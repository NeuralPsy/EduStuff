package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.exception.TaskDoesntExistException;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import il.neuralpsy.edustuff.repository.TaskRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private final SubjectRepository subjectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository,
                       SubjectRepository subjectRepository, ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }


    public TaskDto addTask(TaskDto taskDto){
        Subject subject = subjectRepository.findByName(taskDto.getSubject());
        Task task = mapDtoToTask(taskDto);
        task.setSubject(subject);
        return mapTaskToDto(taskRepository.save(task));
    }

    public Collection<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Integer taskId) {
        TaskDto taskDto;
        try {
            taskDto = mapTaskToDto(taskRepository.findById(taskId).get());
        } catch (EmptyResultDataAccessException e){
            throw new TaskDoesntExistException("There's no task with ID "+taskId);
        }
        return taskDto;
    }

    public Collection<TaskDto> getTasksByUserId(Integer userId) {
        boolean isValid = userRepository.existsById(userId);
        if (!isValid) throw new UserDoesntExistException("There's no user with ID "+userId);
        return taskRepository.findTasksByUser_UserId(userId)
                .stream()
                .map(task -> mapTaskToDto(task))
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


    public boolean setUserForTask(Integer taskId, User student) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        taskRepository.putUserIntoTask(student, timestamp, taskId);
        return true;
    }

    public Collection<TaskDto> getAvailableTasks() {
        return taskRepository.getTaskByUserIsNull()
                .stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toSet());
    }

    private TaskDto mapTaskToDto(Task task){
        TaskDto taskDto = new TaskDto();

        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setTaskStatus(task.getTaskStatus());
        taskDto.setSubject(task.getSubject().getName());
        taskDto.setStartTime(task.getStartTime());
        taskDto.setContent(task.getContent());

        return taskDto;
    }

    private Task mapDtoToTask(TaskDto taskDto){
        Task task = new Task();

        Subject subject = subjectRepository.findByName(taskDto.getSubject());

        task.setTaskId(taskDto.getTaskId());
        task.setName(taskDto.getName());
        task.setTaskStatus(taskDto.getTaskStatus());
        task.setSubject(subject);
        task.setStartTime(taskDto.getStartTime());
        task.setContent(taskDto.getContent());

        return task;
    }
}
