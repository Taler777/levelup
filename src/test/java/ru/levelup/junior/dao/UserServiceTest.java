package ru.levelup.junior.dao;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.levelup.junior.entities.User;
import ru.levelup.junior.web.AppConfig;
import ru.levelup.junior.web.UserService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    UsersDAO dao;

    @Autowired
    private EntityManager manager;

    private long id;

    @Before
    public void setup() {
        manager.getTransaction().begin();
        User testUser = new User("test", "123", 0L);
        userService.create(testUser);
        User testUser2 = new User("test2", "456", 0L);
        userService.create(testUser2);
        manager.getTransaction().commit();
        id = testUser.getId();
    }

    @Test
    public void testGetUser() {
        User found = dao.findById(id);
        assertEquals(id, found.getId());
    }

    @Test
    public void testGetAllUsers(){
        List<User> users = dao.findAllUsers();
        assertEquals(2, users.size());
    }
}
