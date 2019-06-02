package ua.iasa.happyfridge.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.Utils;
import ua.iasa.happyfridge.dto.CreateMealRequest;
import ua.iasa.happyfridge.entities.Ingredient;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.reccomendations.MealUtils;
import ua.iasa.happyfridge.repositories.IngredientRepository;
import ua.iasa.happyfridge.repositories.MealRepository;
import ua.iasa.happyfridge.repositories.OrderRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;


@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;

    private final IngredientRepository ingredientRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @PostMapping("/meals/add")
    public ResponseEntity addMeal(@RequestBody CreateMealRequest request) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String ing : request.getIngredients()) {

            Ingredient ingredient = ingredientRepository.findByName(ing);
            if (ingredient == null) {
                Ingredient newIngredient = new Ingredient(ing);
                ingredientRepository.save(newIngredient);
                ingredientList.add(newIngredient);
            }
            ingredientList.add(ingredient);
        }

        Meal meal = new Meal(request.getName(), request.getAmount(), request.getExpirationDate(),
                request.getDiscount(), request.getPrice(),request.getImageId(), ingredientList);
        mealRepository.save(meal);


        return ResponseEntity.ok().build();
    }

    @GetMapping("/meals/select")
    public List<Meal> selectMeal(){
        Iterable<Meal> all = mealRepository.findAll();
        List<Meal> meals = new ArrayList<>();
        all.forEach(meals::add);
        return meals;
    }

    @GetMapping(path = "/meals/select/{id}")
    public Meal selectMealById(@PathVariable Long id) {
        return mealRepository.getById(id);
    }


    @PutMapping("/meals/update")
    public Meal updateMeal(@RequestBody Meal meal){
        return mealRepository.save(meal);
    }

    @GetMapping("/meals/popularity")
    public LinkedList<Meal> getMealbyPopularity(){
        List<Order> orders = Utils.toList(orderRepository.findAll());
        return MealUtils.getMealbyPopularity(orders, mealRepository, Boolean.TRUE);
    }

    @GetMapping("/reccomendations/admin")
    public LinkedList<Meal> mealRecommendations(){
        List<Order> orders = Utils.toList(orderRepository.findAll());
        LinkedList<Meal> mealbyPopularity = MealUtils.getMealbyPopularity(orders, mealRepository, Boolean.TRUE);
        Meal idealMeal = mealbyPopularity.getFirst();
        HashMap<Meal, Double> mealRate = new HashMap<>();
        for (Meal meal: mealbyPopularity){
            mealRate.put(meal, MealUtils.mealSimilarity(idealMeal, meal));
        }
        return mealRate.entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey).collect(Collectors.toCollection(LinkedList::new));

    }
    @GetMapping("/reccomendations/user")
    public LinkedList<Meal> userRecommendations(Long userId){
        User user = userRepository.getById(userId);
        List<Order> orders = Utils.toList(orderRepository.findAllByUser(user));
        System.out.println(orders);
        return MealUtils.getMealbyPopularity(orders, mealRepository, Boolean.FALSE);
    }
}