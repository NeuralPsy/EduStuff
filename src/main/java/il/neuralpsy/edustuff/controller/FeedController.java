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

    @GetMapping("/{userId}/{role}/all")
    public Collection<FeedEventDto> getAllBasedOnRole(@PathVariable String role, @PathVariable Integer userId){
        return feedService.getAll(role, userId);
    }

}
