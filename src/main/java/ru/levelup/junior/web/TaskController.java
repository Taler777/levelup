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
import ru.levelup.junior.dao.TasksDAO;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.forms.AddTaskForm;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Контроллер, отрабатывающий запросы относительно заданий
 */
@Controller
public class TaskController {
    @Autowired
    TasksDAO dao;

    @GetMapping(path = "/tasks")
    public String getTasks(HttpSession session, ModelMap model) {
        try {
            List<Task> tasks = dao.findAllTasks();
            model.addAttribute("tasks", tasks);
            return "taskList";
        } catch (Exception e) {
            return "mainPage";
        }
    }

    @GetMapping(path = "/addTask")
    public String addTask() {
        return "addTask";
    }

    @PostMapping("/addTask")
    public String registrationForm(
            @Validated
            @ModelAttribute("form") AddTaskForm form,
            BindingResult result
    ) {
        if (form.getName() == null || form.getName().isEmpty()) {
            result.addError(new FieldError("form", "name", "Name ..."));
        }
        dao.create(new Task(form.getName(), form.getText()));
        if (result.hasErrors()) {
            return "addTask";
        }
        return "taskList";
    }

    @ModelAttribute("form")
    public AddTaskForm newFormBean() {
        AddTaskForm bean = new AddTaskForm();
        bean.setName("");
        bean.setText("");

        return bean;
    }
}
