//package ru.levelup.junior.dao;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//import ru.levelup.junior.entities.User;
//import ru.levelup.junior.configs.AppConfig;
//import ru.levelup.junior.web.UserService;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = AppConfig.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    UsersRepository dao;
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    private long id;
//
//    @Before
//    @Transactional
//    public void setup() {
//        User testUser = new User("test", "123", 0L);
//        userService.create(testUser);
//        User testUser2 = new User("test2", "456", 0L);
//        userService.create(testUser2);
//        id = testUser.getId();
//    }
//
//    @Test
//    public void testGetUser() {
//        User found = dao.findById(id).get();
//        assertEquals(id, found.getId());
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        List<User> users = dao.findAll();
//        assertEquals(2, users.size());
//    }
//}
