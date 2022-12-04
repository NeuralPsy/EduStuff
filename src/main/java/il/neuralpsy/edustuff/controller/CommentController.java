package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.CommentDto;
import il.neuralpsy.edustuff.model.Comment;
import il.neuralpsy.edustuff.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public Collection<CommentDto> getAll(){
        return commentService.getAll();
    }

    @PostMapping
    public CommentDto addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @DeleteMapping("/{commentId}")
    public boolean removeComment(@PathVariable Integer commentId){
        return commentService.removeComment(commentId);
    }

    @PutMapping
    public boolean updateComment(@RequestBody CommentDto commentDto){
        return commentService.updateComment(commentDto);
    }
}
