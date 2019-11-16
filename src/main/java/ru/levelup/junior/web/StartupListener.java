package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.dao.UsersDAO;
import ru.levelup.junior.entities.State;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;

import javax.persistence.NoResultException;
import javax.servlet.ServletContextListener;
import java.util.Date;

@Component
public class StartupListener implements ServletContextListener {

    @Autowired
    UsersDAO dao;

    @Autowired
    TasksDAO tasksDAO;

    @EventListener
    @Transactional
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        User testUser;
        User secondUser;

        try {
            testUser = dao.findByLogin("test");
            secondUser = dao.findByLogin("second");
        } catch (NoResultException notFound) {
            testUser = new User("test", "123", 0L);
            secondUser = new User("second", "333", 0L);

            dao.create(testUser);
            dao.create(secondUser);

            for (int i = 0; i < 10; i++) {
                tasksDAO.create(new Task("testName" + i
                        , "testText" + i
                        , null, 5L
                        , State.OPEN, testUser
                        , null
                        , new Date()
                        , null));
            }
        }
    }
}
