package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.iasa.happyfridge.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByUsername(String username);
}
