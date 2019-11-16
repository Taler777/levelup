package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String processLogin(HttpSession session,
                               @RequestParam String login,
                               @RequestParam String password,
                               ModelMap model) {
        try {
            User found = usersDAO.findByLoginAndPassword(login, password);
            session.setAttribute("userId", found.getId());
            return "redirect:/users";
        } catch (NoResultException notFound) {
            model.addAttribute("login", "login");
            return "mainPage";
        }
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String registrationForm(
            @Validated
            @ModelAttribute("form") RegistrationFormBean form,
            BindingResult result
    ) {
        String passwordConfirmation = form.getConfirmation();
        if (passwordConfirmation == null
                || passwordConfirmation.isEmpty()
                || !form.getPassword().equals(passwordConfirmation)) {
            result.addError(new FieldError("form", "passwordConfirmation", "Confirmation doesn't match!!!"));
        }

        try {
            usersDAO.create(new User(form.getLogin(), form.getPassword(), 0L));
        } catch (Exception e) {
            result.addError(new FieldError("form", "login", "User with this login is already registered"));
        }
        if (result.hasErrors()) {
            return "registration";
        }
        return "redirect:/";
    }

    @ModelAttribute("form")
    public RegistrationFormBean newFormBean() {
        RegistrationFormBean bean = new RegistrationFormBean();
        bean.setLogin("");
        bean.setPassword("");
        bean.setConfirmation("");

        return bean;
    }
}