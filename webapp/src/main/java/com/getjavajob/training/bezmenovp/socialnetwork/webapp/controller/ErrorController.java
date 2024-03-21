package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("/common/error-page");
    }

}
