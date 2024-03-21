package com.getjavajob.training.bezmenovp.socialnetwork.service.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class FailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        super.setDefaultFailureUrl("/login");
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("error", 1);
        super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
    }

}