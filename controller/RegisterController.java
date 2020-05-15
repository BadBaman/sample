package com.fabric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fabric.dao.UserRepository;
import com.fabric.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        return modelAndView;
    }

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ModelAndView login(ModelAndView modelAndView, @Valid UserVo userVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            modelAndView.setViewName("register");
            return modelAndView;
        }
        String userName = userVo.getUserName();
        String password = userVo.getPassword();
        UserVo user=new UserVo();
        user.setUserName(userName);
        user.setPassword(password);
        int i=1;
        user.setOrgid(i);
        user.setOrgname("Org1");
        userRepository.save(user);
        log.info(user.toString()+" 保存账号");
        modelAndView.setViewName("mypage");
        modelAndView.addObject("message","注册成功，欢迎使用！");
        modelAndView.addObject("userName",userName);

        return modelAndView;
    }

}
