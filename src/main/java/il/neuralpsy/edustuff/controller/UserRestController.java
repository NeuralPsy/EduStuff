package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService service;


    @Autowired
    public UserRestController(UserService service){
        this.service = service;
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
    public Integer updateUser(@RequestBody UserDto userDto){
        service.updateUser(userDto);
        return userDto.getUserId();
    }

    @DeleteMapping("/{userId}")
    public Integer removeUser(@PathVariable Integer userId){
        service.removeUser(userId);
        return userId;
    }

    @GetMapping
    public Collection<UserDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/userType/{userTypeId}")
    public Collection<UserDto> getAllByUserType(@PathVariable Integer userTypeId){
        return service.getAllByUserType(userTypeId);
    }

}
