package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.FeedEventDto;
import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.event.FeedEvent;
import il.neuralpsy.edustuff.service.FeedService;
import il.neuralpsy.edustuff.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final FeedService feedService;


    @Autowired
    public UserController(UserService service, FeedService feedService){
        this.service = service;
        this.feedService = feedService;
    }

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto userDto){
        return service.addUser(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Integer userId){
        return service.getUserById(userId);
    }

    @GetMapping("/email")
    public UserDto findUserByEmail(@RequestParam String email){
        return service.findUserByEmail(email);
    }

    @PutMapping
    public boolean updateUser(@RequestBody UserDto userDto){
        return service.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public boolean removeUser(@PathVariable Integer userId){
        return service.removeUser(userId);
    }

    @GetMapping
    public Collection<UserDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/userType/{userTypeId}")
    public Collection<UserDto> getAllByUserType(@PathVariable Integer userTypeId){
        return service.getAllByUserType(userTypeId);
    }

    @GetMapping("/{userId}/feed")
    public Collection<FeedEventDto> getUserFeeds(@PathVariable Integer userId){
        return feedService.getUserFeed(userId);
    }
}
