package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.dto.UserRegistrationDto;
import il.neuralpsy.edustuff.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        log.info("Index page request");
        return "index";
    }

    @GetMapping("/feed")
    public String feed(){
        log.info("Feed page request");
        return "feed";
    }

    @GetMapping("/createtask")
    public String createTask(Model model){
        log.info("Create task page request");
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task", taskDto);
        return "createtask";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        log.info("Show registration form");
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                               BindingResult result,
                               Model model){
        log.info("Trying to save user: ");
        boolean doesExist = userService.checkEmailExistence(userRegistrationDto.getEmail());

        if(doesExist){
            log.info("Can not add user");
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            log.info("There's some errors");
            model.addAttribute("user", userRegistrationDto);
            return "register";
        }

        userService.addUser(userRegistrationDto);
        log.info("Success!");
        return "redirect:/register?success";
    }

    @GetMapping("/teacher")
    public String teacher(){
        return "teacher";
    }

    @GetMapping("/student")
    public String student(Model model){
        return "student";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
