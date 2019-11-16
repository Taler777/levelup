package ru.levelup.junior.web;

import ru.levelup.junior.dao.UsersDAO;
import ru.levelup.junior.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersDAO dao;

    public void create(User user) {
        dao.create(user);
    }

    public User getUser(long id) {
        User found = dao.findById(id);
        return found;
    }

    public List<User> getAllUsers() {
        return dao.findAllUsers();
    }
}
