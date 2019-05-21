package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.iasa.happyfridge.entities.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findByName(String name);
}
