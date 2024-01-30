package com.getjavajob.training.bezmenovp.socialnetwork.web;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/account-info")
public class AccountInformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AccountService service;

    public AccountInformationServlet() {
        this.service = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = parseInt(request.getParameter("id"));
        Account account = null;
        account = service.getAccountByID(id);
        request.setAttribute("account", account);
        getServletContext().getRequestDispatcher("/jsp/AccountInformation.jsp").forward(request, response);
    }

}
