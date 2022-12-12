package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.model.Comment;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.TaskRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.service.CommentService;
import il.neuralpsy.edustuff.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public CommentController(CommentService commentService, ApplicationEventPublisher eventPublisher,
                             UserRepository userRepository, TaskRepository taskRepository){
        this.commentService = commentService;
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @PostMapping("/user/{userId}/task/{taskId}")
    public String addComment(@RequestBody String text, @PathVariable Integer userId,
                             @PathVariable Integer taskId, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "tasktempl";
        }

        Comment comment = new Comment();
        text = text.substring(0, text.length()-1).replace("text=", "");

        comment.setText(text);

        User user = userRepository.findById(userId).get();
        Task task = taskRepository.findById(taskId).get();


        comment.setUser(user);
        comment.setTask(task);
        comment.setTimestamp(LocalDateTime.now());

        Integer commentId = commentService.addComment(comment).getCommentId();
        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.COMMENT);
        feedEvent.setUser(user);
        feedEvent.setEventObjectId(commentId);
        feedEvent.setFeedDetails(AllowedFeedEvents.ADD_COMMENT);
        feedEvent.setTimestamp(comment.getTimestamp());

        eventPublisher.publishEvent(feedEvent);
        return "redirect:/task/{taskId}?success";
    }

}
