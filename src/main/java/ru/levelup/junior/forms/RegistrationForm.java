package ru.levelup.junior.forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @Size(min=4, max=32, message = "Login length should be at least 4 and at most 32 characters length")
    @Pattern(regexp = "[a-zA-Z_0-9]+", message = "Only characters, undercscore and numbers resolved.")
    private String login;

    @Size(min=4, max=32, message = "Password length should be at least 4 and at most 32 characters length")
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
