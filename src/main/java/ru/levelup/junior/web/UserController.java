package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.entities.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Контроллер, отрабатывающий запросы относительно пользователей
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public String getUsers(HttpSession session, ModelMap model) {
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "userList";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}
