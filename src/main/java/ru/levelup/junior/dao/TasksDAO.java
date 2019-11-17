package ru.levelup.junior.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.enums.State;
import ru.levelup.junior.entities.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TasksDAO {
    @PersistenceContext
    private EntityManager manager;

    public TasksDAO() {
    }

    public TasksDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Task task) {
        //TODO можно добавить ограничение на создание задания
//		Например, создавать задание может пользователь с рейтингом > 10,
//		то есть, если пользователь выполнил хотя бы несколько заданий с суммарным рейтингом 10 и более
        manager.persist(task);
    }

    public Task findById(long id) {
        return manager.find(Task.class, id);
    }

    public List<Task> findByName(String name) {
        return manager.createQuery("from Task where name = :p", Task.class)
                .setParameter("p", name)
                .getResultList();
    }

    public List<Task> findByState(State state) {
        return manager.createQuery("from Task where state = :p", Task.class)
                .setParameter("p", state)
                .getResultList();
    }

    public List<Task> findAllTasks() {
        return manager.createQuery("from Task", Task.class).getResultList();
    }
}
