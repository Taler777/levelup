package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.entities.Task;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Контроллер, отрабатывающий запросы относительно заданий
 */
@Controller
public class TasksController {
    @Autowired
    TasksDAO dao;

    @GetMapping(path = "/tasks")
    public String dashboard(HttpSession session, ModelMap model) {
        try {
            List<Task> tasks = dao.findAllTasks();
            model.addAttribute("tasks", tasks);
            return "tasksList";
        } catch (Exception e) {
            return "mainPage";
        }
    }
}
