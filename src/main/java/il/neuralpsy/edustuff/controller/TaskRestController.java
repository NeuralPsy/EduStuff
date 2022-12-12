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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("/api/task")
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



    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @PutMapping("/update/{updatorId}")
    public Integer updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer updatorId){

        User user = userRepository.findById(updatorId).get();

        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.TASK);
        feedEvent.setUser(user);
        feedEvent.setFeedDetails(AllowedFeedEvents.UPDATE_TASK);
        feedEvent.setTimestamp(LocalDateTime.now());
        feedEvent.setEventObjectId(taskDto.getTaskId());
        eventPublisher.publishEvent(feedEvent);

        taskService.updateTask(taskDto);

        return taskDto.getTaskId();
    }





}
