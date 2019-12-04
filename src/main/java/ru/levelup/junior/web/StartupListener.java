package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.dao.TasksRepository;
import ru.levelup.junior.dao.UsersRepository;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;
import ru.levelup.junior.enums.State;

import java.util.Date;

@Component
public class StartupListener {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @EventListener
    @Transactional
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        User testUser;
        User secondUser;

            testUser = usersRepository.findByLogin("test");
            secondUser = usersRepository.findByLogin("second");
        if(testUser==null)
        {
            testUser = new User("test", "1234", 0L);
        }
        if(secondUser==null)
        {
            secondUser = new User("second", "3333", 0L);
        }
            usersRepository.save(testUser);
            usersRepository.save(secondUser);

            for (int i = 0; i < 10; i++) {
                tasksRepository.save(new Task("testName" + i
                        , "testText" + i
                        , null, 5L
                        , State.OPEN, testUser
                        , null
                        , new Date()
                        , null));
        }
    }
}
