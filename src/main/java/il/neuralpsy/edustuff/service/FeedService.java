package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.FeedEventDto;
import il.neuralpsy.edustuff.event.*;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.FeedRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedService(UserRepository userRepository, FeedRepository feedRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.feedRepository = feedRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<FeedEventDto> getUserFeed(final Integer userId) {
        return feedRepository.findAllByUser_UserId(userId)
                .stream()
                .map(event -> modelMapper.map(event, FeedEventDto.class))
                .collect(Collectors.toSet());
    }

    @EventListener
    public void handleOnFeedEvent(FeedEvent event) {
        feedRepository.save(event);
    }

}
