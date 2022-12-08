package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.CommentDto;
import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.model.Comment;
import il.neuralpsy.edustuff.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public CommentController(CommentService commentService, ApplicationEventPublisher eventPublisher){
        this.commentService = commentService;
        this.eventPublisher = eventPublisher;
    }


    @PostMapping
    public CommentDto addComment(@ModelAttribute("comment") Comment comment){

        CommentDto commentDto = commentService.addComment(comment);
        FeedEvent feedEvent = new FeedEvent();

        feedEvent.setEventType(EventType.COMMENT);
        feedEvent.setUser(comment.getUser());
        feedEvent.setFeedDetails(AllowedFeedEvents.ADD_COMMENT);
        feedEvent.setTimestamp(comment.getTimestamp());

        eventPublisher.publishEvent(feedEvent);
        return commentDto;
    }

}
