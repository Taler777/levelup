package entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitiesTest
{
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Before
	public void setup() {
		factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
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
	public void testCreateUser() throws Exception {
		User user = new User();
		user.setLogin("test");
		user.setPassword("123");
		manager.getTransaction().begin();
		try {
			manager.persist(user);
			manager.getTransaction().commit();
		}catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
		Assert.assertNotNull(manager.find(User.class, user.getId()));
	}

	@Test
	public void testCreateTask(){

	}

}
