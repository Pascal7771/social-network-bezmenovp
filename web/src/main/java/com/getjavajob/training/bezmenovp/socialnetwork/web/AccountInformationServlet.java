package com.getjavajob.training.bezmenovp.socialnetwork.web;

import com.getjavajob.training.bezmenovp.socialnetwork.domain.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AccountInformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        AccountService accountService = new AccountService();
        List<Account> accountList = accountService.getAllAccounts();

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
