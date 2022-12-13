package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.CommentDto;
import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.exception.NotFoundException;
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
        return commentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public CommentDto addComment(Comment comment) {
        return mapToDto(commentRepository.save(comment));
    }

    public void removeComment(Integer commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (EmptyResultDataAccessException e){
            throw new NotFoundException("There is no comment with ID " + commentId);
        }
    }


    public Collection<CommentDto> getCommentsByTaskId(Integer taskId) {
        return commentRepository.findAllByTask_TaskId(taskId)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .sorted()
                .collect(Collectors.toList());
    }

    public Collection<CommentDto> getCommentsByUserId(Integer userId) {
        return commentRepository.findAllByUser_UserId(userId)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .sorted()
                .collect(Collectors.toList());
    }

    public CommentDto mapToDto(Comment comment){
        UserDto userDto = modelMapper.map(comment.getUser(), UserDto.class);
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setText(comment.getText());
        commentDto.setUser(userDto);
        commentDto.setTimestamp(comment.getTimestamp());

        return commentDto;
    }

}
