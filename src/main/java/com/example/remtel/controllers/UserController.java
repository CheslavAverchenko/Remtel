package com.example.remtel.controllers;


import com.example.remtel.beans.Role;
import com.example.remtel.beans.User;
import com.example.remtel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Pattern PATTERN_FOR_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        return "userList";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles",Role.values());
        return "adminEdit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        userService.saveUser(user, form);
        return "redirect:/user";
    }


    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("name",user.getName());
        model.addAttribute("phonenumber",user.getPhonenumber());
        model.addAttribute("email",user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String phonenumber,
                                @RequestParam String email,
                                Model model){

        if(!validateEmail(email)){
            model.addAttribute("emailError","Invalid E-mail! Click on the Profile in navbar");
            return "profile";
        } else {
            return "redirect:" + userService.editUser(user, email, username, password, name, phonenumber);
        }
    }
    private static boolean validateEmail(String email){
        Matcher matcher = PATTERN_FOR_EMAIL.matcher(email);
        return matcher.find();
    }
}
