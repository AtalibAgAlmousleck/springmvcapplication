package com.codinglevel.controller;

import com.codinglevel.entities.User;
import com.codinglevel.repositorys.UserRepository;
import com.codinglevel.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(value = "/showUsers")
    public String viewUsers(Model model) {
        List<User> users = userServices.viewUser();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/addUserForm")
    public String addUser(User user, Model model) {
      model.addAttribute("user", user);
        return "add-user-form";
    }

    @PostMapping("/saveUser")
    public String registerUser(@ModelAttribute User user) {
        userServices.addUser(user);
        return "redirect:/showUsers";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView updateUser(@RequestParam Long id) {
       ModelAndView modelAndView =
               new ModelAndView("add-user-form");
       User user = userRepository.findById(id).get();
       modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/showUsers";
    }
}
