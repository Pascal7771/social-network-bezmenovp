package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/friends")
public class FriendsController {
    private static final Logger logger = LogManager.getLogger();

    private AccountService service;
    private AccountMapper accountMapper;

    @GetMapping()
    @ResponseBody
    public ModelAndView showFriends(@RequestParam("id") int id, Model model) {
        try {
            List<AccountDto> friendsDtoList = service.getFriends(id).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            model.addAttribute("friends", friendsDtoList);
            return new ModelAndView("/friends/friends");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("common/error-page");
        }
    }

    @PostMapping("/invite")
    public String inviteFriends(@RequestParam("idFriend") int idFriend, @RequestParam("userId") int userId) {
        try {
            service.invite(userId, idFriend);
            return "redirect:/account/info?id=" + idFriend;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping("/invites")
    public ModelAndView showInvites(@RequestParam("id") int id, Model model) {
        try {
            List<AccountDto> invitesDtoList = service.getInvites(id).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            model.addAttribute("invites", invitesDtoList);
            List<AccountDto> outgoingInvitesDtoList = service.getOutgoingInvites(id).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            model.addAttribute("outInvites", outgoingInvitesDtoList);
            return new ModelAndView("/friends/invites");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("common/error-page");
        }
    }

    @PostMapping("/invites/add")
    public String addFriend(@RequestParam("idInviter") int idInviter, @RequestParam("userId") int userId) {
        try {
            service.addFriend(userId, idInviter);
            return "redirect:/friends/invites?id=" + userId;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/invites/reject")
    public String rejectInvite(@RequestParam("idInviter") int idInviter, @RequestParam("userId") int userId) {
        try {
            service.rejectInvite(idInviter, userId);
            return "redirect:/friends/invites?id=" + userId;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/delete")
    public String deleteFriend(@RequestParam("idFriend") int idFriend, @RequestParam("userId") int userId) {
        try {
            service.deleteFriend(userId, idFriend);
            return "redirect:/friends?id=" + userId;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/invites/cancel")
    public String cancelOutgoingInvite(@RequestParam("idInviter") int idInviter, @RequestParam("userId") int userId) {
        try {
            service.rejectInvite(idInviter, userId);
            return "redirect:/friends/invites?id=" + idInviter;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

}