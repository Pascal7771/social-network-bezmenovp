package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.GroupDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.GroupMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.service.GroupService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/group/members")
public class GroupMembersController {
    private static final Logger logger = LogManager.getLogger();

    private GroupService service;
    private GroupMapper groupMapper;
    private AccountMapper accountMapper;

    @GetMapping()
    public ModelAndView getMembers(@RequestParam("id") int id, Model model) {
        try {
            List<AccountDto> membersGroup = service.getMembers(id).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            GroupDto group = groupMapper.groupToService(service.getById(id));
            model.addAttribute("membersGroup", membersGroup);
            model.addAttribute("group", group);
            return new ModelAndView("/group/members-group");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/delete")
    public String deleteMember(@RequestParam("idGroup") int idGroup, @RequestParam("idMember") int idMember) {
        try {
            service.deleteMember(idMember, idGroup);
            return "redirect:/group/members?id=" + idGroup;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/leave")
    public String leave(@RequestParam("idGroup") int idGroup, @RequestParam("idMember") int idMember) {
        try {
            service.deleteMember(idMember, idGroup);
            return "redirect:/group/info?id=" + idGroup;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/invite")
    public String inviteGroup(@RequestParam("idGroup") int idGroup, @RequestParam("idMember") int idMember) {
        try {
            service.invite(idMember, idGroup);
            return "redirect:/group/info?id=" + idGroup;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping("/invites")
    public ModelAndView showInvites(@RequestParam("id") int idGroup, Model model) {
        try {
            List<AccountDto> groupInvites = service.getInvites(idGroup).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            model.addAttribute("invites", groupInvites);
            model.addAttribute("idGroup", idGroup);
            return new ModelAndView("/group/invites");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/invites/add")
    public String addMember(@RequestParam("idGroup") int idGroup, @RequestParam("idInviter") int idInviter) {
        try {
            service.acceptInvite(idInviter, idGroup);
            return "redirect:/group/members/invites?id=" + idGroup;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping("/invites/reject")
    public String rejectInvite(@RequestParam("idGroup") int idGroup, @RequestParam("idInviter") int idInviter) {
        try {
            service.rejectInvite(idInviter, idGroup);
            return "redirect:/group/members/invites?id=" + idGroup;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "/outgoinginvites", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showOutPutGroupInvites(@RequestParam("id") int idAccount, Model model) {
        try {
            List<GroupDto> outgoingGroup = service.getOutgoingInvites(idAccount)
                    .stream().map(group -> groupMapper.groupToService(group)).collect(Collectors.toList());
            model.addAttribute("groups", outgoingGroup);
            model.addAttribute("idAccount", idAccount);
            return new ModelAndView("/group/outgoing-group-invite");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/invites/cancel")
    public String cancelInvite(@RequestParam("idGroup") int idGroup, @RequestParam("idAccount") int idInviter) {
        try {
            service.rejectInvite(idInviter, idGroup);
            return "redirect:/group/members/outgoinginvites?id=" + idInviter;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

}