package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.CommentDto;
import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.model.Comment;
import il.neuralpsy.edustuff.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {
    private final CommentService commentService;

    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public CommentRestController(CommentService commentService, ApplicationEventPublisher eventPublisher){
        this.commentService = commentService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public Collection<CommentDto> getAll(){
        return commentService.getAll();
    }


    @DeleteMapping("/{commentId}")
    public boolean removeComment(@PathVariable Integer commentId){
        return commentService.removeComment(commentId);
    }

    @GetMapping("/task/{taskId}")
    public Collection<CommentDto> getCommentsByTaskId(@PathVariable Integer taskId){
        return commentService.getCommentsByTaskId(taskId);
    }

    @GetMapping("/user/{userId}")
    public Collection<CommentDto> getCommentsByUserId(@PathVariable Integer userId){
        return commentService.getCommentsByUserId(userId);
    }


}
