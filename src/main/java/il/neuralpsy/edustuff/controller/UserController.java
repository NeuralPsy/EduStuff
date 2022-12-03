package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @GetMapping("/{studentId}")
    public Optional<User> getUserById(@PathVariable Integer studentId){
        return service.getUserById(studentId);
    }

    @GetMapping("/email")
    public Optional<User> findUserByEmail(@RequestParam String email){
        return service.findUserByEmail(email);
    }

    @PutMapping
    public boolean updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public boolean removeUser(@PathVariable Integer userId){
        return service.removeUser(userId);
    }

    @GetMapping
    public Collection<User> getAll(){
        return service.gelAll();
    }
}
