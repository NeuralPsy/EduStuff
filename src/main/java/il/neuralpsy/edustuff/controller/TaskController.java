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
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper){
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public TaskDto addTask(@RequestBody Task task){
        return modelMapper.map(taskService.addTask(task), TaskDto.class);
    }


    @GetMapping
    public Collection<TaskDto> getAll(){
        return taskService.getAll().stream().map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{taskId}")
    public TaskDto getTaskById(@PathVariable Integer taskId){
        return modelMapper.map(taskService.getTaskById(taskId).get(), TaskDto.class);
    }

    @GetMapping("/user/{userId}")
    public Collection<TaskDto> getTasksByUserId(@PathVariable Integer userId){
        return taskService.getTasksByUserId(userId).stream().map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public boolean updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(modelMapper.map(taskDto, Task.class));
    }

    @DeleteMapping("/{taskId}")
    public boolean removeTask(@PathVariable Integer taskId){
        return taskService.removeTask(taskId);
    }


}
