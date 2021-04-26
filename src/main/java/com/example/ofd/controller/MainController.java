package com.example.ofd.controller;

import com.example.ofd.entity.Balance;
import com.example.ofd.entity.User;
import com.example.ofd.repo.BalanceRepo;
import com.example.ofd.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BalanceRepo balanceRepo;

    @Autowired
    private MainService mainService;

    //Страница балансов
    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user,
                       Model model) {
        Iterable<Balance> list = balanceRepo.findAll();
        List<Balance> list1 = mainService.filterByType((List<Balance>) list, "Fixed");
        model.addAttribute("list", list1);
        model.addAttribute("user", user);
        return "main";
    }
    //Добавление баланса пользователю
    @PostMapping("/")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam (defaultValue = "0") Integer balance,
                      @RequestParam String type,
                      Model model) {
        mainService.add(balance, type, user);
        Iterable<Balance> list = balanceRepo.findAll();
        List<Balance> list1 = mainService.filterByType((List<Balance>) list, "Fixed");
        model.addAttribute("list", list1);
        return "redirect:/";
    }
    //Страница редактирования баланса
    @GetMapping("{id}/edit")
    public String editGet(@PathVariable(value = "id") Long id,
                          Model model) {
        Balance balanceNum = balanceRepo.findById(id).orElseThrow(IllegalStateException::new);
        model.addAttribute("balance", balanceNum);
        return "edit";
    }
    //Метод пост редактирования баланса
    @PostMapping("{id}/edit")
    public String editPost(@PathVariable(value = "id") Long id,
                           @RequestParam Integer balance) {
        mainService.editBalance(id, balance);
        return "redirect:/";
    }
    //Геттер отображения ошибок при логине пользователя
    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
}
