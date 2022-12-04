package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }


    @GetMapping
    public Collection<TaskDto> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public TaskDto getTaskById(@PathVariable Integer taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/user/{userId}")
    public Collection<TaskDto> getTasksByUserId(@PathVariable Integer userId){
        return taskService.getTasksByUserId(userId);
    }

    @PutMapping
    public boolean updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto);
    }

    @DeleteMapping("/{taskId}")
    public boolean removeTask(@PathVariable Integer taskId){
        return taskService.removeTask(taskId);
    }


}
