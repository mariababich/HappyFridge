package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.iasa.happyfridge.entities.Meal;

import java.util.List;

public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {
    Meal getById(Long id);
    List<Meal> findByIdIn(List<Long> id);
}
