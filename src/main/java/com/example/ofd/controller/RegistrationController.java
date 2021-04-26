package com.example.ofd.controller;

import com.example.ofd.entity.Role;
import com.example.ofd.entity.User;
import com.example.ofd.repo.UserRepo;
import com.example.ofd.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MainService mainService;
    //Страница регистрации пользователя
    @GetMapping
    public String registration() {
        return "registration";
    }
    //Пост метод регистрации пользователя
    @PostMapping
    public String add(User user,
                      Map<String, Object> model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.put("message", "Error №1 — Пользователь с таким логином уже существует");
            return "registration";
        }
        user.setId(mainService.countNum());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        mainService.add(0, "Fixed", user);
        return "redirect:/login";
    }
}
