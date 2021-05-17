package com.qzy.boot.controller;

import com.qzy.boot.entities.BillProvider;
import com.qzy.boot.entities.User;
import com.qzy.boot.mapper.BillMapper;
import com.qzy.boot.mapper.ProviderMapper;

import com.qzy.boot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Resource
    private ProviderMapper providerMapper;

    @Resource
    private BillMapper billMapper;

    @Resource
    private UserMapper userMapper;


    @GetMapping("/users")
    public String list(Model model, User user){
        List<User> users = null;
        if (user!=null && user.getUsername()!=null && user.getUsername()!=""){
            users=userMapper.getUsers(user);
        }else {
            users = userMapper.getUsers(null);
        }
        model.addAttribute("userList", users);
        return "user/list";
    }


    @GetMapping("/user/{id}")
    public String one(@PathVariable(name = "id") Integer id, Model model,
                      @RequestParam(value = "type",defaultValue = "view") String type){
        User userById = userMapper.getUserById(id);
        model.addAttribute("user", userById);
//        model.addAttribute("billProviderNames", providerMapper.getAllProviderName());
        if ("view".equals(type)){
            return "user/view";
        }else if ("update".equals(type)){
            return "user/update";
        }else {
            return "redirect:/";
        }
    }

    @Transactional
    @PutMapping("/user")
    public String update(User user){
        userMapper.updateUser(user);
        return "redirect:/users";
    }
//
//    @GetMapping("/bill")
//    public String getAdd(Model model){
//        Set billProviderName = providerMapper.getAllProviderName();
//        model.addAttribute("billProviderName",billProviderName);
//        return "bill/add";
//    }
//
//    @Transactional
//    @PostMapping("/bill")
//    public String add(BillProvider billProvider){
////        providerDao.save(provider);
//        billProvider.setCreateDate(new Date());
//        int providerPidByName = providerMapper.getProviderPidByName(billProvider.getProviderName());
//        billProvider.setPid(providerPidByName);
//        billMapper.addBill(billProvider);
//        return "redirect:/bills";
//    }
//
//
//    @DeleteMapping("/bill/{bid}")
//    public String delete(@PathVariable("bid") Integer bid){
//        billMapper.deleteBillByBid(bid);
//        return "redirect:/bills";
//    }
}
