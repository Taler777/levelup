package entities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import static org.junit.Assert.*;

public class SmokeTest
{
	private EntityManagerFactory factory;
	private EntityManager manager;

	@Before
	public void setup()
	{
		factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
		manager = factory.createEntityManager();
	}

	@After
	public void cleanup()
	{
		if (manager != null)
		{
			manager.close();
		}
		if (factory != null)
		{
			factory.close();
		}
	}

	@Test
	public void testCreateAccount() throws Exception
	{
		Account account = new Account();
		account.setLogin("test");
		account.setPassword("123");

		manager.getTransaction().begin();
		try
		{
			manager.persist(account);
			manager.getTransaction().commit();
		}
		catch (Exception e)
		{
			manager.getTransaction().rollback();
			throw e;
		}
		assertNotNull(manager.find(Account.class, account.getId()));
	}

	@Test
	public void queryAccount()
	{
		Account account = new Account();
		account.setLogin("test");
		account.setPassword("123");

		Account receiver = new Account();
		receiver.setLogin("another");
		receiver.setPassword("456");

		Transaction tx = new Transaction();
		tx.setAmount(10);
		tx.setOrigin(account);
		tx.setReceiver(receiver);
		tx.setTime(new Date());

		manager.getTransaction().begin();
		try
		{
			manager.persist(account);
			manager.persist(receiver);
			manager.persist(tx);

			manager.getTransaction().commit();
		}
		catch (Exception e)
		{
			manager.getTransaction().rollback();
			throw e;
		}

		Account found = manager.createQuery("from Account where login = :p", Account.class)
			.setParameter("p", "test")
			.getSingleResult();

		assertEquals(account.getId(), found.getId());
		assertEquals("123", found.getPassword());

		List<Transaction> found2 = manager.createQuery("from Transaction t where t.origin.login = :p", Transaction.class)
			.setParameter("p", "test")
			.getResultList();

		assertEquals(1, found2.size());
		assertEquals((tx.getId()), found2.get(0).getId());
	}

	//TODO поправить тест
	@Test
	@Ignore
	public void criteriaBuilder()
	{
		Account account = new Account();
		account.setLogin("test");
		account.setPassword("123");

		manager.getTransaction().begin();
		try
		{
			manager.persist(account);
			manager.getTransaction().commit();
		}
		catch (Exception e)
		{
			manager.getTransaction().rollback();
			throw e;
		}


		CriteriaBuilder builder = factory.getCriteriaBuilder();
		CriteriaQuery query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);

		Path<Object> login = root.get("login");
		query.where(builder.equal(root.get("login"), "test"));
		Account found  = (Account)manager.createQuery(query).getSingleResult();

		assertEquals(account.getId(), found.getId());
	}
}
