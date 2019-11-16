package ru.levelup.junior.dao;

import ru.levelup.junior.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class UsersDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UsersDAO dao;
    private User user;

    @Before
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new UsersDAO(manager);
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
    public void create() throws Exception {
        manager.getTransaction().begin();
        try {
            dao.create(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        assertNotNull(manager.find(User.class, user.getId()));
    }

    @Test
    public void findByLogin() throws Exception {
        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        User found = dao.findByLogin("test");
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }

    @Test
    public void findByLoginAndPassword() throws Exception {
        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        User found = dao.findByLoginAndPassword("test", "123");
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }
}