package ua.iasa.happyfridge.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
     List<Order> findAllByUser(User user);
}
