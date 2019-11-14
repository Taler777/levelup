package ru.levelup.junior.dao;

import ru.levelup.junior.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UsersDAO {

    private EntityManager manager;

    @Autowired
    public UsersDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(User user) {
        manager.persist(user);
    }

    public User findById(long id) {
        return manager.find(User.class, id);
    }

    public User findByLogin(String login) {
        return manager.createQuery("from User where login = :p", User.class)
                .setParameter("p", login)
                .getSingleResult();
    }

    public User findByLoginAndPassword(String login, String password) {
        return manager.createQuery("from User where login = :login AND password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }

    public List<User> findAllUsers() {
        return manager.createQuery("from User", User.class).getResultList();
    }
}
