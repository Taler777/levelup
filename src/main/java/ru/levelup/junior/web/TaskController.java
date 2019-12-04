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
import ru.levelup.junior.dao.TasksRepository;
import ru.levelup.junior.dao.UsersRepository;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;
import ru.levelup.junior.forms.AddTaskForm;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Контроллер, отрабатывающий запросы относительно заданий
 */
@Controller
public class TaskController {
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(path = "/tasks")
    public String getTasks(HttpSession session, ModelMap model) {
        try {
            List<Task> tasks = tasksRepository.findAll();
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
            BindingResult result,
            HttpSession session
    ) {
        if (form.getName() == null || form.getName().isEmpty()) {
            result.addError(new FieldError("form", "name", "Name ..."));
        }
        if (form.getText() == null || form.getText().isEmpty()) {
            result.addError(new FieldError("form", "text", "Text ..."));
        }
        User author = usersRepository.findById((long) session.getAttribute("userId")).get();
        tasksRepository.save(new Task(form.getName(), form.getText(), author, new Date()));
        if (result.hasErrors()) {
            return "addTask";
        }
        return "redirect:/tasks";
    }

    @ModelAttribute("form")
    public AddTaskForm newFormBean() {
        AddTaskForm bean = new AddTaskForm();
        bean.setName("");
        bean.setText("");

        return bean;
    }
}
