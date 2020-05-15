package com.fabric.controller;

//import com.example.contact.Dao.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class coreController{

    @RequestMapping("/hello")
    public String home(){

        return "hello";
    }
    @RequestMapping("/history")
    public String index(){

        return "history";
    }
    /*@RequestMapping("/file")
    public String file(){

        return "file";
    }*/
    @RequestMapping("/fabricnetwork")
    public String fabricnetwork(){
        return "fabricnetwork";

    }
    @RequestMapping("/transaction")
    public String transaction(){
        return "transaction";

    }
    @RequestMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin")!=null){
            model.addAttribute("name", session.getAttribute("username"));
        }
        return "mypage";
    }
}
