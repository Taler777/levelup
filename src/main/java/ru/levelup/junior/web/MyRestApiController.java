package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levelup.junior.dao.TasksRepository;
import ru.levelup.junior.entities.Task;
import ru.levelup.junior.entities.User;

import java.util.List;

@RestController
public class MyRestApiController
{
	@Autowired
	private TasksRepository tasksRepository;
	@GetMapping("/api/tasks/find")
	public List<Task> findByUser(@RequestParam long userId){
		return tasksRepository.findByAuthorId(userId);

	}
}
