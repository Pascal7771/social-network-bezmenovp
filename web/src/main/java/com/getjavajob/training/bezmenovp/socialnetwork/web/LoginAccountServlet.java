package com.getjavajob.training.bezmenovp.socialnetwork.web;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static java.lang.String.valueOf;

@WebServlet("/login")
public class LoginAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountService service;

    public LoginAccountServlet() {
        this.service = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountName = (String) session.getAttribute("accountName");
        String accountID = (String) session.getAttribute("accountID");
        if (accountName == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("accountEmail")) {
                        request.setAttribute("accountEmail", cookie.getValue());
                    } else if (cookie.getName().equals("accountPassword")) {
                        request.setAttribute("accountPassword", cookie.getValue());
                    }
                }
                getServletContext().getRequestDispatcher("/jsp/LoginAccount.jsp").forward(request, response);
            }
        } else {
            String accountInfo = request.getContextPath() + "/account-info?id=" + accountID;
            response.sendRedirect(accountInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountEmail = request.getParameter("accountEmail");
        String accountPassword = request.getParameter("accountPassword");
        try {
            Account account = service.getAccountByEmail(accountEmail);
            if (account != null && account.getAccountPassword().equals(accountPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("accountName", account.getAccountName());
                session.setAttribute("accountID", valueOf(account.getAccountID()));
                String useCookies = request.getParameter("remember");
                if (useCookies != null) {
                    Cookie cookieEmail = new Cookie("accountEmail", accountEmail);
                    Cookie cookiePass = new Cookie("accountPassword", accountPassword);
                    cookieEmail.setMaxAge(60 * 60 * 24);
                    cookiePass.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookieEmail);
                    response.addCookie(cookiePass);
                }
                String accountInfo = request.getContextPath() + "/account-info?id=" + account.getAccountID();
                response.sendRedirect(accountInfo);
            } else {
                request.setAttribute("error", 1);
                getServletContext().getRequestDispatcher("/jsp/LoginAccount.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
