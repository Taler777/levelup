package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.entities.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersListController {
    @Autowired
    private UserService userService;

    @GetMapping(params = "/users")
    public String dashboard(HttpSession session, ModelMap model) {
        try {
//            long userId = (Long) session.getAttribute("userId");
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "users";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}
