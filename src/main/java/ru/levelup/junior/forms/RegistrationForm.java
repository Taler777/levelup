package ru.levelup.junior.forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Форма при регистрации
 */
public class RegistrationForm {

    private String login;

    private String password;

    private String passwordConfirmation;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
