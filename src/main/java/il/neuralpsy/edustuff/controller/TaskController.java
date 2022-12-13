package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.exception.NotFoundException;
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

@SuppressWarnings({"SpellCheckingInspection", "SameReturnValue"})
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

    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") TaskDto taskDto,
                             @RequestParam String teacherId, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "createtask";
        }

        if (userRepository.findUserByEmail(teacherId).isEmpty()){
            throw new NotFoundException("User with ID "+teacherId+" doesn't exist");
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

    @PostMapping("/{taskId}/student/{studentId}")
    public String takeTask(@PathVariable Integer taskId, @PathVariable Integer studentId){
        User student;

         if (userRepository.findById(studentId).isPresent()){
             student = userRepository.findById(studentId).get();
         } else {
             throw new NotFoundException("Returned data is null or user doesnt exist");
         }

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

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/{taskId}")
    public String getTaskInfo(@PathVariable Integer taskId){

        return "tasktempl";
    }

    @DeleteMapping("/makedone/{taskId}/user/{userId}")
    public String removeTask(@PathVariable Integer taskId, @PathVariable Integer userId){
        taskService.removeTask(taskId);

        if (userRepository.findById(userId).isEmpty()){
            throw new NotFoundException("User with ID "+userId+" doesn't exist");
        }

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
