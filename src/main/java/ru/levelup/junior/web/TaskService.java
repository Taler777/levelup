package ru.levelup.junior.web;

import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TasksDAO dao;

    public void create(Task task) {
        dao.create(task);
    }

    public Task getTask(long id){
        Task found = dao.findById(id);
        return found;
    }

    public List<Task> getAllTasks() {
        return dao.findAllTasks();
    }
}
