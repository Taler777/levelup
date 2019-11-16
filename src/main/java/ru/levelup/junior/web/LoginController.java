package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelup.junior.dao.UsersDAO;
import ru.levelup.junior.entities.User;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends HttpServlet {

    @Autowired
    UsersDAO usersDAO;

    @PostMapping(path = "/login")
    public String processLogin(HttpSession session, @RequestParam String login, @RequestParam String password, ModelMap model) {
        try {
            User found = usersDAO.findByLoginAndPassword(login, password);
            session.setAttribute("userId", found.getId());
            return "redirect:/users";
        } catch (NoResultException notFound) {
            model.addAttribute("login", "login");
            return "mainPage";
        }
    }
}