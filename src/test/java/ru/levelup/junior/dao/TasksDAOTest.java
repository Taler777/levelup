//package ru.levelup.junior.dao;
//
//import ru.levelup.junior.enums.State;
//import ru.levelup.junior.entities.Task;
//import ru.levelup.junior.entities.User;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import static org.junit.Assert.*;
//
//public class TasksDAOTest {
//    private EntityManagerFactory factory;
//    private EntityManager manager;
//    private TasksDAO dao;
//    private Task task;
//    private User user;
//
//    @Before
//    public void setUp() throws Exception {
//        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
//        manager = factory.createEntityManager();
//        dao = new TasksDAO(manager);
//        user = new User();
//        user.setLogin("test");
//        user.setPassword("123");
//        task = new Task();
//        task.setName("testName");
//        task.setAuthor(user);
//        task.setRating(5L);
//        task.setState(State.OPEN);
//        task.setDateOpen(new Date());
//    }
//
//    @After
//    public void cleanup() {
//        if (manager != null) {
//            manager.close();
//        }
//        if (factory != null) {
//            factory.close();
//        }
//    }
//
//    @Test
//    public void create() throws Exception {
//        manager.getTransaction().begin();
//        try {
//            manager.persist(user);
//            dao.create(task);
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            throw e;
//        }
//        assertNotNull(manager.find(Task.class, task.getId()));
//    }
//
//    @Test
//    public void findByName() throws Exception {
//        manager.getTransaction().begin();
//        try {
//            manager.persist(user);
//            manager.persist(task);
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            throw e;
//        }
//
//        List<Task> found = dao.findByName("testName");
//        assertNotNull(found.get(0));
//        assertEquals(task.getId(), found.get(0).getId());
//    }
//
//    @Test
//    public void findByState() throws Exception {
//        manager.getTransaction().begin();
//        try {
//            manager.persist(user);
//            manager.persist(task);
//            manager.getTransaction().commit();
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            throw e;
//        }
//
//        List<Task> found = dao.findByState(State.OPEN);
//        assertNotNull(found.get(0));
//        assertEquals(task.getState(), found.get(0).getState());
//    }
//}