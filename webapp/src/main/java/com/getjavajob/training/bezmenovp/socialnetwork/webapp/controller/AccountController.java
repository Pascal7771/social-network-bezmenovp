package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Roles;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.MessageDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.MessageMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@AllArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LogManager.getLogger();
    private AccountService accountService;
    private MessageService messageService;
    private AccountMapper accountMapper;
    private MessageMapper messageMapper;
    private PasswordEncoder passwordEncoder;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.setDisallowedFields("file");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        try {
            return new ModelAndView("/account/create-account");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AccountDto accountDto) throws ValidateException {
        try {
            Account account = accountMapper.accountToData(accountDto);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountService.create(account);
            return "redirect:/account/login-account";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping("/info")
    public ModelAndView getInfo(@RequestParam("id") int id, Model model, HttpServletRequest req) {
        try {
            Account accountData = accountService.getById(id);
            AccountDto account = accountMapper.accountToService(accountData);
            List<Message> messageWallData = messageService.getByAppointment(account.getId(), "wall");
            List<MessageDto> messageWall = messageWallData
                    .stream().map(message -> messageMapper.messageToService(message)).collect(Collectors.toList());
            HttpSession session = req.getSession();
            Account accountUser = accountService.getById(parseInt(valueOf(session.getAttribute("userId"))));
            List<AccountDto> friendsDtoList = accountService.getFriends(id).stream()
                    .map(accountFriend -> accountMapper.accountToService(accountFriend)).collect(Collectors.toList());
            boolean contains = friendsDtoList.contains(accountMapper.accountToService(accountUser));
            List<AccountDto> outGoingInvites = accountService.getOutgoingInvites(accountUser.getId()).stream()
                    .map(invites -> accountMapper.accountToService(invites)).collect(Collectors.toList());
            boolean isInvite = outGoingInvites.contains(accountMapper.accountToService(accountData));
            model.addAttribute("contains", contains);
            model.addAttribute("isInvite", isInvite);
            model.addAttribute("account", account);
            model.addAttribute("messageWall", messageWall);
            model.addAttribute("isAdminAccount", isAdmin(accountData));
            model.addAttribute("isAdminUser", isAdmin(accountUser));
            return new ModelAndView("/account/account-information");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    private boolean isAdmin(Account account) {
        return account.getRole() == Roles.ADMIN;
    }

    @GetMapping("/avatar")
    public void printAvatar(@RequestParam("id") int id, HttpServletResponse resp) throws IOException {
        Account accountData = accountService.getById(id);
        AccountDto account = accountMapper.accountToService(accountData);
        resp.getOutputStream().write(account.getImg());
    }

    @PostMapping("/update-form")
    public ModelAndView showUpdate(@RequestParam("id") int id, Model model) {
        try {
            Account accountData = accountService.getById(id);
            AccountDto account = accountMapper.accountToService(accountData);
            model.addAttribute("account", account);
            return new ModelAndView("/account/update-account");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/update-form-file")
    public ModelAndView showUpdateFromXml(@RequestParam("id") int id, Model model, @ModelAttribute("file") MultipartFile file) {
        try {
            InputStream fileInput = file.getInputStream();
            Account accountData = accountService.convertFromXml(id, fileInput);
            AccountDto account = accountMapper.accountToService(accountData);
            model.addAttribute("account", account);
            return new ModelAndView("/account/update-account");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("accountRequest") AccountDto accountDto
            , @RequestParam(value = "deleteAvatar", required = false) String delPhoto) throws ValidateException {
        try {
            Account account = accountService.getById(accountDto.getId());
            if (delPhoto != null) {
                accountDto.setImg(null);
            } else if (accountDto.getImg().length == 0) {
                accountDto.setImg(account.getImg());
            }
            accountDto.setRole(account.getRole().toString());
            if (!account.getPassword().equals(accountDto.getPassword())) {
                accountMapper.accountToData(account, accountDto);
                account.setPassword(passwordEncoder.encode(account.getPassword()));
                accountService.update(account);
            } else {
                accountMapper.accountToData(account, accountDto);
                accountService.update(account);
            }
            return "redirect:/account/info?id=" + account.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        try {
            accountService.deleteById(id);
            return "redirect:/logout";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/groups")
    public ModelAndView accountGroupsList(@RequestParam("id") int idAccount, Model model) {
        try {
            Set<Group> accountGroupsList = accountService.getAccountGroupsList(idAccount);
            model.addAttribute("accountGroupsList", accountGroupsList);
            return new ModelAndView("/account/account-groups");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/make-admin/{id}")
    public String makeAdmin(@PathVariable("id") int id) {
        try {
            accountService.makeAdmin(id);
            return "redirect:/account/info?id=" + id;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

}