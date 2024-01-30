package com.getjavajob.training.bezmenovp.socialnetwork.web;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        AccountService accountService = new AccountService();
        List<Account> accountList = accountService.getAllAccounts();

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Account List</h1>");
            out.println("<table>");
            out.println("<tr>" +
                    "<th>Name, Surname</th>" +
                    "<th>Phone</th>" +
                    "<th>Work Phone</th>" +
                    "<th>HomeAddress</th>" +
                    "</tr>");
            for (Account account : accountList) {
                out.println("<tr>");
                out.println("<td>" + account.getAccountName() + " " + account.getAccountSurname() + "</td>");
                out.println("<td>" + account.getAccountPhone() + "</td>");
                out.println("<td>" + account.getAccountWorkPhone() + "</td>");
                out.println("<td>" + account.getAccountHomeAddress() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        }

    }

}
