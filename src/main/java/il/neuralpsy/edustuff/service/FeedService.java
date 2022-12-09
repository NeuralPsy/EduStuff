package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.FeedEventDto;
import il.neuralpsy.edustuff.event.*;
import il.neuralpsy.edustuff.repository.FeedRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
                .map(event -> mapToDto(event))
                .collect(Collectors.toSet());
    }

    @EventListener
    public void handleOnFeedEvent(FeedEvent event) {
        feedRepository.save(event);
    }

    public Collection<FeedEventDto> getStudentsFeed() {
        return feedRepository.findAllByUserUserType_Name("STUDENT")
                .stream()
                .map(event -> mapToDto(event)).collect(Collectors.toSet());
    }

    public Collection<FeedEventDto> getAll(String role, Integer userId) {
        if (role.equalsIgnoreCase("student")) {
            return feedRepository.findAllByUser_UserId(userId)
                    .stream()
                    .map(event -> mapToDto(event))
                    .collect(Collectors.toSet());
        }
        if (role.equalsIgnoreCase("teacher")) {
            return feedRepository.findAll()
                    .stream()
                    .map(event -> mapToDto(event))
                    .collect(Collectors.toSet());
        }



        return feedRepository.findAll()
                .stream()
                .map(event -> mapToDto(event))
                .collect(Collectors.toList());
    }

    private FeedEventDto mapToDto(FeedEvent feedEvent){
        FeedEventDto feedEventDto = new FeedEventDto();

        String eventType = feedEvent.getEventType().name();
        String operationType = feedEvent.getFeedDetails().getOperation().name();

        String userName = "";
        if (feedEvent.getUser() != null) {
            userName = feedEvent.getUser().getName();
        }

        feedEventDto.setTimestamp(feedEvent.getTimestamp());
        feedEventDto.setEventId(feedEvent.getEventId());
        feedEventDto.setObjectName(eventType);
        feedEventDto.setUserName(userName);
        feedEventDto.setOperationType(operationType);
        feedEventDto.setObjectId(feedEvent.getEventObjectId());

        return feedEventDto;
    }
}
