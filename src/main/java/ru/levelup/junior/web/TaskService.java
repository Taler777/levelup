package ru.levelup.junior.web;

import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TasksDAO dao;

    public Task getTask(long id){

        return null;
    }


}
