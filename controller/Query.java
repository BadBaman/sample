/*
package com.example.contact.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Query {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("query")
    @ResponseBody
    public String query(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public String create(HttpServletRequest request, @RequestParam(value="channel",defaultValue="mychannel") String channel_name) {
        log.warn((String) request.getSession().getAttribute("isLogin"));
        log.warn(channel_name);
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
    @RequestMapping(value="/join",method = RequestMethod.POST)
    @ResponseBody
    public String join(HttpServletRequest request, @RequestParam(value="channel",defaultValue="mychannel")String channel_name) {
        log.warn((String) request.getSession().getAttribute("isLogin"));
        log.warn(channel_name);
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
    @RequestMapping(value="/install",method = RequestMethod.POST)
    @ResponseBody
    public String install(HttpServletRequest request, @RequestParam String channel_name,
                          @RequestParam String chaincodePath,@RequestParam String chaincodeName,@RequestParam String chainCodeVersion) {
        log.warn((String) request.getSession().getAttribute("isLogin"));
        log.warn(channel_name);
        log.warn(chaincodePath);
        log.warn(chaincodeName);
        log.warn(chainCodeVersion);
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
    @RequestMapping(value="/instantiate",method = RequestMethod.POST)
    @ResponseBody
    public String instantiate(HttpServletRequest request, @RequestParam String channel_name,
                              @RequestParam String peerWithOrgs) {
        log.warn((String) request.getSession().getAttribute("isLogin"));
        log.warn(peerWithOrgs);

        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
    @RequestMapping(value="/upgrade",method = RequestMethod.POST)
    @ResponseBody
    public String upgrade(HttpServletRequest request, @RequestParam String channel_name,@RequestParam String peerWithOrgs,
                              @RequestParam String chaincodePath,@RequestParam String chaincodeName,@RequestParam String chainCodeVersion) {
        log.warn((String) request.getSession().getAttribute("isLogin"));
        log.warn(channel_name);
        log.warn(peerWithOrgs);
        log.warn(chaincodePath);
        log.warn(chaincodeName);
        log.warn(chainCodeVersion);
        HttpSession session = request.getSession();
        session.setAttribute("hello","123123123");
        log.warn((String) session.getAttribute("hello"));
        return String.valueOf(session.getAttribute("hello"));

    }
}
*/
