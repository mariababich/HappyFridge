package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.iasa.happyfridge.entities.Meal;

import java.util.List;

public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {
    Meal getById(Long id);
    List<Meal> findByIdIn(List<Long> id);
}
