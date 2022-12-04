package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.CommentDto;
import il.neuralpsy.edustuff.exception.CommentDoesntExistException;
import il.neuralpsy.edustuff.model.Comment;
import il.neuralpsy.edustuff.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<CommentDto> getAll() {
        return commentRepository.findAll().stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    public CommentDto addComment(Comment comment) {
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    public boolean removeComment(Integer commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (EmptyResultDataAccessException e){
            throw new CommentDoesntExistException("There is no comment with ID " + commentId);
        } return true;
    }

    public boolean updateComment(CommentDto commentDto) {
        commentRepository.updateComment(commentDto.getTimestamp(), true, commentDto.getText(),
                commentDto.getCommentId());
        return true;
    }
}
