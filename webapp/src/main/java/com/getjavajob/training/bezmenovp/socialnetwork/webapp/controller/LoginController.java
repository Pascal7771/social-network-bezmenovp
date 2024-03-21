package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            return "redirect:/account/info?id=" + userId;
        }
        return "/account/login-account";
    }

}