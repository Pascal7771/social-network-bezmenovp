package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.GroupDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.MessageDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.GroupMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.MessageMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.GroupMessageService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.GroupService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@AllArgsConstructor
@Controller
@RequestMapping("/group")
public class GroupController {
    private static final Logger logger = LogManager.getLogger();

    private GroupService groupService;
    private GroupMapper groupMapper;
    private AccountMapper accountMapper;
    private AccountService accountService;
    private GroupMessageService groupMessageService;
    private MessageMapper messageMapper;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        binder.setDisallowedFields("file");
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        return new ModelAndView("/group/create-group");
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("group") GroupDto groupRequest, @RequestParam("idCreator") int id) throws ValidateException {
        try {
            Group group = groupMapper.groupToData(groupRequest);
            Group groupData = groupService.create(group);
            groupService.invite(id, group.getId());
            groupService.acceptInvite(id, group.getId());
            return "redirect:/group/info?id=" + groupData.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping(value = "/info")
    public ModelAndView getInfo(@RequestParam("id") int id, Model model, HttpServletRequest req) {
        try {
            Group groupData = groupService.getById(id);
            GroupDto group = groupMapper.groupToService(groupData);
            model.addAttribute(group);
            List<AccountDto> membersGroup = groupService.getMembers(id).stream()
                    .map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
            List<MessageGroup> messageWallData = groupMessageService.getByAppointment(id, "wall");
            List<MessageDto> messageWall = messageWallData
                    .stream().map(message -> messageMapper.messageGroupToService(message)).collect(Collectors.toList());
            HttpSession session = req.getSession();
            Account accountData = accountService.getById(parseInt(valueOf(session.getAttribute("userId"))));
            List<AccountDto> inviter = groupData.getInviter().stream()
                    .map(invites -> accountMapper.accountToService(invites)).collect(Collectors.toList());
            boolean isInvite = inviter.contains(accountMapper.accountToService(accountData));
            boolean contains = membersGroup.contains(accountMapper.accountToService(accountData));
            model.addAttribute("contains", contains);
            model.addAttribute("isInvite", isInvite);
            model.addAttribute("messageWall", messageWall);
            return new ModelAndView("/group/group-information");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @GetMapping("/avatar")
    public void printAvatar(@RequestParam("id") int id, HttpServletResponse resp) throws IOException {
        Group groupData = groupService.getById(id);
        GroupDto group = groupMapper.groupToService(groupData);
        resp.getOutputStream().write(group.getImg());
    }

    @GetMapping("/update")
    public ModelAndView showUpdate(@RequestParam("id") int id, Model model) {
        try {
            Group groupData = groupService.getById(id);
            GroupDto group = groupMapper.groupToService(groupData);
            if (group != null) {
                model.addAttribute("groupRequest", group);
                return new ModelAndView("/group/update-group");
            } else {
                model.addAttribute("error", "Группы не сущствует");
                return new ModelAndView("/common/error-page");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("groupRequest") GroupDto groupRequest
            , @RequestParam(value = "deleteAvatar", required = false) String delPhoto) throws ValidateException {
        try {
            Group group = groupService.getById(groupRequest.getId());
            if (delPhoto != null) {
                groupRequest.setImg(null);
            } else if (groupRequest.getImg().length == 0) {
                groupRequest.setImg(group.getImg());
            }
            groupMapper.groupToData(group, groupRequest);
            groupService.update(group);
            return "redirect:/group/info?id=" + group.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") int id, @RequestParam("idAccount") int idAccount) {
        try {
            groupService.deleteById(id);
            return "redirect:/account/info?id=" + idAccount;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

}