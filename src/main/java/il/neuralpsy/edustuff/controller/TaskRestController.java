package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    private final TaskService taskService;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TaskRestController(TaskService taskService, ApplicationEventPublisher eventPublisher, UserRepository userRepository){
        this.taskService = taskService;
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;

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



    @PutMapping("/update/{updatorId}")
    public boolean updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer updatorId){

        User user = userRepository.findById(updatorId).get();

        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.TASK);
        feedEvent.setUser(user);
        feedEvent.setFeedDetails(AllowedFeedEvents.UPDATE_TASK);
        feedEvent.setTimestamp(LocalDateTime.now());
        feedEvent.setEventObjectId(taskDto.getTaskId());
        eventPublisher.publishEvent(feedEvent);

        return taskService.updateTask(taskDto);
    }


    @DeleteMapping("/{taskId}")
    public boolean removeTask(@PathVariable Integer taskId){
        return taskService.removeTask(taskId);
    }



}
