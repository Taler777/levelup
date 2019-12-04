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
import ru.levelup.junior.dao.UsersRepository;
import ru.levelup.junior.entities.User;
import ru.levelup.junior.forms.RegistrationForm;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(path = "/login")
    public String processLogin(HttpSession session,
                               @RequestParam String login,
                               @RequestParam String password,
                               ModelMap model) {
        User found = usersRepository.findByLoginAndPassword(login, password);
        if (found == null) {
            model.addAttribute("login", "password");
            return "mainPage";
        }
        session.setAttribute("userId", found.getId());
        return "redirect:/users";
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String registrationForm(
            @Validated
            @ModelAttribute("form") RegistrationForm form,
            BindingResult result
    ) {
        String passwordConfirmation = form.getPasswordConfirmation();
        if (passwordConfirmation == null
                || passwordConfirmation.isEmpty()
                || !form.getPassword().equals(passwordConfirmation)) {
            result.addError(new FieldError("form", "passwordConfirmation", "Confirmation doesn't match!!!"));
        }

        try {
            usersRepository.save(new User(form.getLogin(), form.getPassword(), 0L));
        } catch (Exception e) {
            result.addError(new FieldError("form", "login", "User with this login is already registered"));
        }
        if (result.hasErrors()) {
            return "registration";
        }
        return "redirect:/";
    }

    @ModelAttribute("form")
    public RegistrationForm newFormBean() {
        RegistrationForm bean = new RegistrationForm();
        bean.setLogin("");
        bean.setPassword("");
        bean.setPasswordConfirmation("");

        return bean;
    }
}