package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.exception.TaskDoesntExistException;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.TaskRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Collection<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Integer taskId) {
        return taskRepository.findById(taskId);
    }

    public Collection<Task> getTasksByUserId(Integer userId) {
        boolean isValid = userRepository.existsById(userId);
        if (!isValid) throw new UserDoesntExistException("There's no user with ID "+userId);
        return taskRepository.findTasksByUser_UserId(userId);
    }

    public boolean updateTask(Task task) {
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
