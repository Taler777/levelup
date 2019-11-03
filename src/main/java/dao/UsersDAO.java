package dao;

import entities.User;

import javax.persistence.EntityManager;

public class UsersDAO
{
	private EntityManager manager;

	public UsersDAO(EntityManager manager)
	{
		this.manager = manager;
	}

	public void create(User user)
	{
		manager.persist(user);
	}

	public User findByLogin(String login)
	{
		return manager.createQuery("from User where login = :p", User.class)
			.setParameter("p", login)
			.getSingleResult();
	}

	public User findByLoginAndPassword(String login, String password)
	{
		return manager.createQuery("from User where login = :login AND password = :password", User.class)
			.setParameter("login", login)
			.setParameter("password", password)
			.getSingleResult();
	}
}
