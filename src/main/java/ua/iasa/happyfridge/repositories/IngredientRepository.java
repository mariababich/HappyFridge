package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.iasa.happyfridge.entities.Ingredient;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {
    Ingredient findByName(String name);
    Ingredient getById(Long id);
}
