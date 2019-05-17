package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.dto.CreateMealRequest;
import ua.iasa.happyfridge.entities.Ingredient;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.repositories.IngredientRepository;
import ua.iasa.happyfridge.repositories.MealRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MealController {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

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
                request.getDiscount(), request.getPrice(), ingredientList);
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
}