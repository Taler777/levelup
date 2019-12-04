package ru.levelup.junior.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Класс, описывающий пользователя - участника сервиса борьбы с расклейкой рекламы в неположенных местах
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 32, unique = true, nullable = false)
    @Size(min = 4, max = 32, message = "Login length should be at least 4 and at most 32 characters length")
    @Pattern(regexp = "[a-zA-Z_0-9]+", message = "Only characters, undercscore and numbers resolved.")
    private String login;

    @Column(length = 32, nullable = false)
    @Size(min = 4, max = 32, message = "Password length should be at least 4 and at most 32 characters length")
    @JsonIgnore
    private String password;

    private long rating;

    @OneToMany(mappedBy = "author")
    private List<Task> authorTasks;

    @OneToMany(mappedBy = "executor")
    private List<Task> executorTasks;

    public User() {
    }

    public User(String login, String password, long rating) {
        this.login = login;
        this.password = password;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public List<Task> getAuthorTasks() {
        return authorTasks;
    }

    public void setAuthorTasks(List<Task> authorTasks) {
        this.authorTasks = authorTasks;
    }

    public List<Task> getExecutorTasks() {
        return executorTasks;
    }

    public void setExecutorTasks(List<Task> executorTasks) {
        this.executorTasks = executorTasks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("login", login)
                .append("rating", rating)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(rating, user.rating)
                .append(login, user.login)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(login)
                .append(rating)
                .toHashCode();
    }
}
