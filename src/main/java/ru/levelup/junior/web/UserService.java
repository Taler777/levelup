//package ru.levelup.junior.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.levelup.junior.dao.UsersRepository;
//import ru.levelup.junior.entities.User;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    UsersRepository dao;
////    private UsersDAO dao;
//
//    public void create(User user) {
//        dao.save(user);
//    }
//
//    public User getUser(long id) {
//        return dao.findById(id).get();
//    }
//
//    public List<User> getAllUsers() {
//        return dao.findAll();
//    }
//}
