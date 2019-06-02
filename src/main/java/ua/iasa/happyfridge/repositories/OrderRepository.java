package ua.iasa.happyfridge.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.iasa.happyfridge.entities.Meal;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;

import java.util.List;
import java.util.Map;

@Transactional
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
     List<Order> findAllByUser(User user);

//     @Query("SELECT mealList, COUNT(mealList) as c FROM orders GROUP BY mealList ORDER BY c DESC")
//     Map<Meal, Integer> mealByPopularity();
}
