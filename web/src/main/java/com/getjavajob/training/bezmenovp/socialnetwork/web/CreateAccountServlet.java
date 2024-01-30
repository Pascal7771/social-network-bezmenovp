package com.getjavajob.training.bezmenovp.socialnetwork.web;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@WebServlet("/create-account")
@MultipartConfig
public class CreateAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AccountService service;

    public CreateAccountServlet() {
        this.service = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/CreateAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accountPassword = request.getParameter("accountPassword");
        String accountSurname = request.getParameter("accountSurname");
        String accountName = request.getParameter("accountName");
        String accountPatronymic = request.getParameter("accountPatronymic");
        LocalDate accountBirthday = LocalDate.parse(request.getParameter("accountBirthday"));
        String accountPhone = request.getParameter("accountPhone");
        String accountWorkPhone = request.getParameter("accountWorkPhone");
        String accountHomeAddress = request.getParameter("accountHomeAddress");
        String accountBusinessAddress = request.getParameter("accountBusinessAddress");
        String accountEmail = request.getParameter("accountEmail");
        String accountICQ = request.getParameter("accountICQ");
        String accountSkype = request.getParameter("accountSkype");
        String accountAdditionalInformation = request.getParameter("accountAdditionalInformation");
        Part filePart = request.getPart("accountImage");
        InputStream accountImage = filePart.getInputStream();
        Account account = service.createAccount(accountPassword, accountSurname, accountName, accountPatronymic,
                accountBirthday, accountPhone, accountWorkPhone, accountHomeAddress, accountBusinessAddress,
                accountEmail, accountICQ, accountSkype, accountAdditionalInformation, accountImage);
        HttpSession session = request.getSession();
        session.setAttribute("accountName", account.getAccountName());
        session.setAttribute("accountID", String.valueOf(account.getAccountID()));
        String accountInfo = request.getContextPath() + "/account-info?id=" + account.getAccountID();
        response.sendRedirect(accountInfo);
    }

}
