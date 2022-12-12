package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.exception.TaskDoesntExistException;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.TaskStatus;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import il.neuralpsy.edustuff.repository.TaskRepository;
import il.neuralpsy.edustuff.repository.TaskStatusRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private final TaskStatusRepository statusRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository,
                       SubjectRepository subjectRepository, TaskStatusRepository statusRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.statusRepository = statusRepository;
    }


    public TaskDto addTask(TaskDto taskDto){
        Subject subject = subjectRepository.findByName(taskDto.getSubject());
        Task task = mapDtoToTask(taskDto);
        task.setSubject(subject);
        task.setTaskStatus(statusRepository.findByName("AVAILABLE"));
        return mapTaskToDto(taskRepository.save(task));
    }

    public Collection<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(task -> mapTaskToDto(task))
                .collect(Collectors.toSet());
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
                .collect(Collectors.toSet());
    }

    public boolean updateTask(TaskDto taskDto) {
        Task task = mapDtoToTask(taskDto);
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
        LocalDateTime timestamp = LocalDateTime.now();
        taskRepository.putUserIntoTask(student, timestamp, taskId);
        taskRepository.setTaskStatus(statusRepository.findById(1).get(), timestamp, taskId);
        return true;
    }


    public TaskDto mapTaskToDto(Task task){
        TaskDto taskDto = new TaskDto();

        Subject subject = task.getSubject();

        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setStatus(task.getTaskStatus().getName());
        taskDto.setSubject(subject.getName());
        taskDto.setStartTime(task.getStartTime());
        taskDto.setContent(task.getContent());


        return taskDto;
    }

    public Task mapDtoToTask(TaskDto taskDto){
        Task task = new Task();

        Subject subject = subjectRepository.findByName(taskDto.getSubject());

        TaskStatus taskStatus = statusRepository.findByName(taskDto.getStatus());

        task.setTaskId(taskDto.getTaskId());
        task.setName(taskDto.getName());
        task.setTaskStatus(taskStatus);
        task.setSubject(subject);
        task.setStartTime(taskDto.getStartTime());
        task.setContent(taskDto.getContent());

        return task;
    }
}
