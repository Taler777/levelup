//package ru.levelup.junior.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.levelup.junior.dao.TasksRepository;
//import ru.levelup.junior.entities.Task;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    @Autowired
//    TasksRepository tasksRepository;
//
//    public void create(Task task) {
//        tasksRepository.save(task);
//    }
//
//    public Task getTask(long id) {
//        Task found = tasksRepository.findById(id);
//        return found;
//    }
//
//    public List<Task> getAllTasks() {
//        return tasksRepository.findAll();
//    }
//}
