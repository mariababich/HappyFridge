package ua.iasa.happyfridge.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.iasa.happyfridge.entities.File;

public interface FileRepository extends CrudRepository<File, Long> {
}
