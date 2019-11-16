package ru.levelup.junior.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntitiesTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private User user;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

        user = new User();
        user.setLogin("test");
        user.setPassword("123");
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testCreateUser() throws Exception {
        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        assertNotNull(manager.find(User.class, user.getId()));

        User found = manager.createQuery("from User where login = :p", User.class)
                .setParameter("p", "test")
                .getSingleResult();

        assertEquals(user.getId(), found.getId());
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setName("testName");
        task.setText("testText");
        task.setRating(5L);
        task.setAuthor(user);
        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.persist(task);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        List<Task> found = manager.createQuery("from Task where author.login =:p ", Task.class)
                .setParameter("p", "test")
                .getResultList();

        assertNotNull(found);
        assertEquals(task.getId(), found.get(0).getId());
        assertEquals(task.getRating(), found.get(0).getRating());
    }
}