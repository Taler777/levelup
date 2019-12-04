package ru.levelup.junior.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.junior.entities.User;

import java.util.List;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<User, Long> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    List<User> findAll();

    @Query("select u from User u where u.login= :login")
    List<User> findByXxx(String login);
}
