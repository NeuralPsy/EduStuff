package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping
    public Collection<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public Optional<Task> getTaskById(@PathVariable Integer taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/user")
    public Collection<Task> getTasksByUserId(@RequestParam Integer id){
        return taskService.getTasksByUserId(id);
    }

    @PutMapping
    public boolean updateTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public boolean removeTask(@PathVariable Integer taskId){
        return taskService.removeTask(taskId);
    }


}
