package com.qzy.boot.controller;


import com.qzy.boot.entities.User;
import com.qzy.boot.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class LoginController {
    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session) {
        List<User> users = userMapper.getUsers(null);
        boolean flag = false;
        for (User x : users) {
            if (x.getUsername().equals(username) && x.getPassword().equals(password)){
                flag=true;
            }
        }
//        if (username != null && "123".equals(password)) {
//            session.setAttribute("loginUser", username);
//            return "redirect:main.html";
//        }
         if (flag) {
            session.setAttribute("loginUser", username);
            return "redirect:main.html";
        }
        model.addAttribute("msg", "用户名或密码错误");
//        map.put("msg", "用户名密码错误");
        return "main/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/";
    }


}
