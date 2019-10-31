package entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistencwUnit");
        manager = factory.createEntityManager();
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
    public void testCreateAccount() throws Exception {
        Account account = new Account();
        account.setLogin("test");
        account.setPassword("123");
        manager.getTransaction().begin();
        try {
            manager.persist(account);
            manager.getTransaction().commit();
        }catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        Assert.assertNotNull(manager.find(Account.class, account.getId()));
    }
}
