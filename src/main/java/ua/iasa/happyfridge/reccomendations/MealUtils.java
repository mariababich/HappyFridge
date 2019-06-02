package ua.iasa.happyfridge.reccomendations;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import ua.iasa.happyfridge.Utils;
import ua.iasa.happyfridge.entities.Ingredient;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.repositories.MealRepository;
import ua.iasa.happyfridge.repositories.OrderRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

@UtilityClass
public class MealUtils {

    public static Double mealSimilarity(Meal meal1, Meal meal2){
        List<Ingredient> ingredients1 = meal1.getIngredients();
        List<Ingredient> ingredients2 = meal2.getIngredients();
        Set<Ingredient> result = ingredients1.stream()
                .distinct()
                .filter(ingredients2::contains)
                .collect(Collectors.toSet());
        Double price1 = meal1.getPrice();
        Double price2 = meal2.getPrice();
        if (price1!= null && price2!=null){
            return 5*result.size()-Math.abs(price1-price2);
        }
        else {
            return  (double) result.size();
        }
    }

    public static LinkedList<Meal> getMealbyPopularity(List<Order> orders, MealRepository mealRepository, Boolean noOrder){
        Map<Meal, Integer> meals = orders.stream().flatMap(order -> order.getMealList().stream()).collect(Collectors.toConcurrentMap(
                w -> w, w -> 1, Integer::sum));
        LinkedList<Meal> pop = meals.entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey).collect(Collectors.toCollection(LinkedList::new));
//        System.out.println(pop);
        if (noOrder==Boolean.TRUE) {
        List<Meal> unpop = Utils.toList(mealRepository.findAll());
        for (Meal meal:unpop){
            if (!pop.contains(meal)){
                pop.addLast(meal);
            }
        } }
        return pop;
    }

}
