package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.iasa.happyfridge.entities.Order;
import ua.iasa.happyfridge.entities.User;

import java.util.List;

@Transactional
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
     List<Order> findAllByUser(User user);
}
