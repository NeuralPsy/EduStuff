package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SuppressWarnings("SpellCheckingInspection")
@Controller
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TaskController(TaskService taskService, ApplicationEventPublisher eventPublisher, UserRepository userRepository){
        this.taskService = taskService;
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;

    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") TaskDto taskDto,
                             @RequestParam String teacherId, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "createtask";
        }

        User teacher = userRepository.findUserByEmail(teacherId).get();

        FeedEvent feedEvent = new FeedEvent();

        Integer taskId = taskService.addTask(taskDto).getTaskId();

        feedEvent.setEventType(EventType.TASK);
        feedEvent.setEventObjectId(taskId);
        feedEvent.setUser(teacher);
        feedEvent.setFeedDetails(AllowedFeedEvents.CREATE_TASK);
        feedEvent.setTimestamp(LocalDateTime.now());

        eventPublisher.publishEvent(feedEvent);


        return "redirect:/createtask?success";
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @PostMapping("/{taskId}/student/{studentId}")
    public String takeTask(@PathVariable Integer taskId, @PathVariable Integer studentId){

        User student = userRepository.findById(studentId).get();

        taskService.setUserForTask(taskId, student);

        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.TASK);
        feedEvent.setUser(student);
        feedEvent.setFeedDetails(AllowedFeedEvents.TAKE_TASK);
        feedEvent.setTimestamp(LocalDateTime.now());
        feedEvent.setEventObjectId(taskId);
        eventPublisher.publishEvent(feedEvent);

        return "redirect:/tasks?success";
    }

    @GetMapping("/{taskId}")
    public String getTaskInfo(@PathVariable Integer taskId){
        log.info("You are in Task Controller getting task info");

        return "tasktempl";
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @DeleteMapping("/makedone/{taskId}/user/{userId}")
    public String removeTask(@PathVariable Integer taskId, @PathVariable Integer userId){
        taskService.removeTask(taskId);

        User user = userRepository.findById(userId).get();


        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.TASK);
        feedEvent.setUser(user);
        feedEvent.setFeedDetails(AllowedFeedEvents.DELETE_TASK);
        feedEvent.setTimestamp(LocalDateTime.now());
        feedEvent.setEventObjectId(taskId);
        eventPublisher.publishEvent(feedEvent);


        return "tasktempl";
    }



}
