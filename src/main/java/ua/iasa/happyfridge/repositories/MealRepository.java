package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.iasa.happyfridge.entities.Meal;



@RepositoryRestResource(collectionResourceRel ="meals", path="meals")
public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {
}
