package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.dao.UsersDAO;
import ru.levelup.junior.entities.State;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

//@Component
public class StartupListener implements ServletContextListener {
    @Autowired
    private EntityManagerFactory factory;

    @Autowired
    EntityManager manager;

    @Autowired
    UsersDAO dao;

    @Autowired
    TasksDAO tasksDAO;

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt){
        User testUser;
        User secondUser;

        manager.getTransaction().begin();

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

            manager.getTransaction().commit();
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        EntityManagerFactory factory = getFactory(event.getServletContext());

        if (factory != null) {
            factory.close();
        }
    }

    public static EntityManagerFactory getFactory(ServletContext context) {
        return (EntityManagerFactory) context.getAttribute("factory");
    }
}
