package ru.levelup.junior.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.Task;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, Long> {
    Task findById(long id);

    List<Task> findAll();

    List<Task> findByAuthorId(long id);
}
