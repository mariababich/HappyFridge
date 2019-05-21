package ua.iasa.happyfridge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.iasa.happyfridge.entities.Ingredient;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Role;
import ua.iasa.happyfridge.entities.User;
import ua.iasa.happyfridge.repositories.IngredientRepository;
import ua.iasa.happyfridge.repositories.MealRepository;
import ua.iasa.happyfridge.repositories.RoleRepository;
import ua.iasa.happyfridge.repositories.UserRepository;

import java.util.Arrays;

import static java.util.Collections.singleton;

@Service
@RequiredArgsConstructor
public class InitService implements SmartInitializingSingleton {

    private final MealRepository mealRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final MailService mailService;
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    private Ingredient ing1 = new Ingredient("sugar");
    private Ingredient ing2 = new Ingredient("milk");
    private Ingredient ing3 = new Ingredient("broccoli");
    private Ingredient ing4 = new Ingredient("tofu");

    private Meal meal1 = new Meal("vegan burger", 2, null, null, 12.5, Arrays.asList(ing2, ing4));
    private Meal meal2 = new Meal("raw candy", 3, null, null, 12.5, Arrays.asList(ing1, ing2));
    private Meal meal3 = new Meal("broccoli", 4, null, null,13.9, Arrays.asList(ing3));

    private Role roleuser = new Role("USER");
    private Role roleadmin = new Role("ADMIN");

    @Override
    public void afterSingletonsInstantiated() {
        ingredientRepository.save(ing1);
        ingredientRepository.save(ing2);
        ingredientRepository.save(ing3);
        ingredientRepository.save(ing4);
        mealRepository.save(meal1);
        mealRepository.save(meal2);
        mealRepository.save(meal3);
        roleRepository.save(roleuser);
        roleRepository.save(roleadmin);
        User useradmin = new User("admin", bCryptPasswordEncoder.encode("admin"), "admin.email", singleton(roleRepository.findByName("ADMIN")));
        userRepository.save(useradmin);
    }

}
