package ua.iasa.happyfridge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iasa.happyfridge.dto.CreateIngredientRequest;;
import ua.iasa.happyfridge.entities.Ingredient;
import ua.iasa.happyfridge.repositories.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients/select")
    public List<Ingredient> selectIngredients() {
        Iterable<Ingredient> all = ingredientRepository.findAll();
        List<Ingredient> ingredients = new ArrayList<>();
        all.forEach(ingredients::add);
        return ingredients;

    }

    @GetMapping(path = "/ingredients/select/{id}")
    public Ingredient selectIngredientById(@PathVariable Long id) {
        return ingredientRepository.getById(id);
    }

    @PostMapping("/ingredients/add")
    public Ingredient addIngredient(@RequestBody CreateIngredientRequest request){
        Ingredient ingredient = new Ingredient(request.getName(), request.getAmount(), request.getMeasure());
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    @PutMapping("/ingredients/update")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }
}
