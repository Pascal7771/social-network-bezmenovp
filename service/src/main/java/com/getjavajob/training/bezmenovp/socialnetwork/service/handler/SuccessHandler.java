package com.getjavajob.training.bezmenovp.socialnetwork.service.handler;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public SuccessHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        Account account = accountRepository.findById(accountRepository.findByEmail(authentication.getName()).getId()).orElse(null);
        session.setAttribute("email", account.getEmail());
        session.setAttribute("password", account.getPassword());
        session.setAttribute("userName", account.getName());
        session.setAttribute("lastName", account.getSurname());
        session.setAttribute("userId", account.getId());
        session.setAttribute("role", account.getRole());
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/account/info?id=" + account.getId());
    }

}