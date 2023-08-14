package com.auth.registrationlogindemo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.auth.registrationlogindemo.dto.UserDto;
import com.auth.registrationlogindemo.entity.User;
import com.auth.registrationlogindemo.service.UserService;

import java.util.List;

@Controller
public class AuthuenticationController {

    private UserService userService;

    public AuthuenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to manage user registration form request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to save/register user 
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        
        if (existingUser != null) {
        	
            result.rejectValue("email", null, "This email is already registered");
        }
        
        if (result.hasErrors()) {
        	
            model.addAttribute("user", user);
            
            return "register";
        }
        
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    // handler method to find all users
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
    	
        List<UserDto> users = userService.findAllUsers();
        
        model.addAttribute("users", users);
        
        return "users";
    }
}
