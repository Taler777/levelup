package dao;

import entities.State;
import entities.Task;

import java.util.List;
import javax.persistence.EntityManager;

public class TasksDAO
{
	private EntityManager manager;

	public TasksDAO(EntityManager manager)
	{
		this.manager = manager;
	}

	public void create(Task task)
	{
		manager.persist(task);
	}

	public List<Task> findByName(String name){
		return manager.createQuery("from Task where name = :p", Task.class)
			.setParameter("p", name)
			.getResultList();
	}

	public List<Task> findByState(State state){
		return manager.createQuery("from Task where state = :p", Task.class)
			.setParameter("p", state)
			.getResultList();
	}
}
