package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.TaskDto;
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

@SuppressWarnings("ALL")
@Controller
@Slf4j
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/tasks")
    public String tasks(){
        return "tasks";
    }


    @GetMapping("/createtask")
    public String createTask(Model model){
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task", taskDto);
        return "createtask";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                               BindingResult result,
                               Model model){
        boolean doesExist = userService.checkEmailExistence(userRegistrationDto.getEmail());

        if(doesExist){
            result.rejectValue("email", null,
                    "There is an account already registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userRegistrationDto);
            return "register";
        }

        userService.addUser(userRegistrationDto);
        log.info("Success!");
        return "redirect:/register?success";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
