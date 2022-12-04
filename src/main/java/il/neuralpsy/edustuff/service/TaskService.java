package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.exception.TaskDoesntExistException;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.TaskRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public TaskDto addTask(Task task){
        return modelMapper.map(taskRepository.save(task), TaskDto.class);
    }

    public Collection<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Integer taskId) {
        TaskDto taskDto;
        try {
            taskDto = modelMapper.map(taskRepository.findById(taskId), TaskDto.class);
        } catch (EmptyResultDataAccessException e){
            throw new TaskDoesntExistException("There's no task with ID "+taskId);
        }
        return taskDto;
    }

    public Collection<TaskDto> getTasksByUserId(Integer userId) {
        boolean isValid = userRepository.existsById(userId);
        if (!isValid) throw new UserDoesntExistException("There's no user with ID "+userId);
        return taskRepository.findTasksByUser_UserId(userId).stream().map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public boolean updateTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.updateTaskStatus(task.getTaskStatus(), task.getTaskId());
        return true;
    }

    public boolean removeTask(Integer taskId) {
        boolean isValid = taskRepository.existsById(taskId);
        if (!isValid) throw new TaskDoesntExistException("There's no task with ID "+taskId);

        taskRepository.deleteById(taskId);
        return true;
    }

    public boolean giveTaskToUser(Integer taskId, Integer userId) {
        taskRepository.findById(taskId).get().setUser(userRepository.findById(userId).get());
//
//        userRepository.update(user.getName(), user.getEmail(), user.getBirthdate(), userId);
////        taskRepository.updateTask(task.getName(), task.getStartTime(), task.getTaskStatus(), taskId);
        return true;
    }
}
