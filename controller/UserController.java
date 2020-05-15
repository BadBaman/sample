package com.fabric.controller;

import com.fabric.entity.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fabric.dao.UserRepository;
import com.fabric.entity.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    /*@RequestMapping("/register") // Map ONLY GET REQUESTs.
    public @ResponseBody String addNewUser (@RequestParam String username
            ,  @RequestParam String password, UserVo user) {
        // @ResponseBody means the returned String is a response, not a view name.
        user.setUserName(username);
        user.setPassword(password);
        userRepository.save(user);
        log.info(user.toString()+" 保存成功");
        return "mypage";
    }

*
     * 登陆方法, 用户输入邮箱和密码, 查询数据库检验是否有该账户,如果有,
     * 返回原先页面 ,登陆成功。
     * @param email 用户邮箱
     * @param password 用户密码
     * @param model Spring MVC中的Model，用来储存经过controller处理后的信息，再由View层渲染
     *         得到前端页面。
     * @return


    @GetMapping(path = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
            List<UserVo> users = userRepository.findByUserName(username);
        // 如果数据库中未查到该账号:
        if (users == null) {
            log.warn("attempting to log in with the non-existed account");
            return "该用户不存在";
        } else {
            UserVo user = users.get(0);
            if (user.getPassword().equals(password)) {
                // 如果密码与邮箱配对成功:
                model.addAttribute("name", user.getUserName());
                log.warn(user.toString()+ " logged in");
            } else {
                // 如果密码与邮箱不匹配:
                model.addAttribute("name", "logging failed");
                log.warn(user.toString()+ " failed to log in");
            }
            return "mypage";
        }
    }*/
    @GetMapping(path="/all")
    public String getAllUsers(Model model) {
        Iterable<UserVo> users=userRepository.findAll();
        model.addAttribute("index" , users);
        return "users";
    }

    @GetMapping(path="/")
    public String welcomePage(@RequestParam(name="name", required=false, defaultValue="World")
                                      String name)
    {
        return "login";
    }
}
