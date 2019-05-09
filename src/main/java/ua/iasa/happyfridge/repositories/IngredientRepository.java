package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.iasa.happyfridge.entities.Ingredient;

@RepositoryRestResource(collectionResourceRel = "ingredients", path="ingredients")
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {
    public Ingredient findByName(String name);
}
