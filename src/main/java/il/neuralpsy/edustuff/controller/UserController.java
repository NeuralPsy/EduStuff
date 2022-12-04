package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService service, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(service.addUser(user), UserDto.class);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Integer userId){
        return modelMapper.map(service.getUserById(userId).get(), UserDto.class);
    }

    @GetMapping("/email")
    public UserDto findUserByEmail(@RequestParam String email){
        return modelMapper.map(service.findUserByEmail(email), UserDto.class);
    }

    @PutMapping
    public boolean updateUser(@RequestBody UserDto userDto){
        return service.updateUser(modelMapper.map(userDto, User.class));
    }

    @DeleteMapping("/{userId}")
    public boolean removeUser(@PathVariable Integer userId){
        return service.removeUser(userId);
    }

    @GetMapping
    public Collection<UserDto> getAll(){
        return service.gelAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/userType/{userTypeId}")
    public Collection<UserDto> getAllByUserType(@PathVariable Integer userTypeId){
        return service.getAllByUserType(userTypeId).stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
