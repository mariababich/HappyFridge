package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.iasa.happyfridge.entities.User;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByUsername(String username);
    User getById(Long id);
}
