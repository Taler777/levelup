package web;

import dao.TasksDAO;
import dao.UsersDAO;
import entities.State;
import entities.Task;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class StartupListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");

        User testUser;
        User secondUser;

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        UsersDAO dao = new UsersDAO(manager);
        TasksDAO tx = new TasksDAO(manager);

        try {
            testUser = dao.findByLogin("test");
            secondUser = dao.findByLogin("second");

        } catch (NoResultException notFound) {
            testUser = new User("test", "123", 0L);
            secondUser = new User("second", "333", 0L);

            dao.create(testUser);
            dao.create(secondUser);

            for (int i = 0; i < 10; i++) {
                tx.create(new Task("testName"+i, "testText"+i, null, 5L, State.OPEN, testUser, null, new Date(), null));
            }

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }

        event.getServletContext().setAttribute("factory", factory);
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
