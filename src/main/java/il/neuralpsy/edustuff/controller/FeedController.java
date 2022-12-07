package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.FeedEventDto;
import il.neuralpsy.edustuff.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;


    @Autowired
    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }

    @GetMapping("/students/{userId}")
    public Collection<FeedEventDto> getUserFeeds(@PathVariable Integer userId){
        return feedService.getUserFeed(userId);
    }

    @GetMapping("/students")
    public Collection<FeedEventDto> getAllStudentFeeds(){
        return feedService.getStudentsFeed();
    }

    @GetMapping("/own")
    public Collection<FeedEventDto> getOwnFeeds(@RequestParam("userId") Integer userId){
        return feedService.getUserFeed(userId);
    }
}
