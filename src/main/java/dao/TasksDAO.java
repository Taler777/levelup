package dao;

import entities.State;
import entities.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TasksDAO {
    private EntityManager manager;

    public TasksDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Task task) {
        //TODO можно добавить ограничение на создание задания
//		Например, создавать задание может пользователь с рейтингом > 10,
//		то есть, если пользователь выполнил хотя бы несколько заданий с суммарным рейтингом 10 и более
        manager.persist(task);
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
