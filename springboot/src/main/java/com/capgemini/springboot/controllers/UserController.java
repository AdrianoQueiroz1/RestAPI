package com.capgemini.springboot.controllers;

import com.capgemini.springboot.entities.User;
import com.capgemini.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/index.html")
    public String showIndexPage() {
        return "index";
    }
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @GetMapping("/register/ola")
    public String redirection(){
        return "redirect:/mambo.html";
    }
    @GetMapping("/mambo")
    public String mambo(){
        return "mambo";
    }
    @GetMapping("/mambo.html")
    public String mambo2(){
        return "mambo";
    }
    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String treatLoginForm(@ModelAttribute("user") User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if(userService.authenticateUser(username,password)){
            return "login_success";
        }else{
            return "redirect:login?error";
        }
    }
    @GetMapping("/login_success")
    public String loginSuccess(){
        return "login_success";
    }

}
