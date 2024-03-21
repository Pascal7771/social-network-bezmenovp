package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.MessageDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.MessageMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.MessageService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@Controller
@MultipartConfig
@RequestMapping("/account/message")
public class AccountMessageController {
    private static final Logger logger = LogManager.getLogger();

    MessageService messageService;
    MessageMapper messageMapper;
    private AccountService accountService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.setDisallowedFields("file");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @GetMapping("/create")
    public ModelAndView showCreate(@RequestParam("id") int recipientId, @RequestParam("appointment") String appointment, Model model) {
        try {
            model.addAttribute("recipientId", recipientId);
            model.addAttribute("appointment", appointment);
            return new ModelAndView("/message/create-message");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MessageDto messageRequest) throws ValidateException {
        try {
            Message message = messageMapper.messageToData(messageRequest);
            Message messageData = messageService.create(message);
            return "redirect:/account/info?id=" + messageData.getRecipientId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping("/img")
    public void printAvatar(@RequestParam("id") int id, HttpServletResponse resp) throws IOException {
        Message messageData = messageService.getById(id);
        MessageDto message = messageMapper.messageToService(messageData);
        resp.getOutputStream().write(message.getImg());
    }

    @GetMapping("/create-personal")
    public ModelAndView showCreatePersonal(@RequestParam("id") int recipientId, @RequestParam("appointment") String appointment, Model model) {
        try {
            model.addAttribute("recipientId", recipientId);
            model.addAttribute("appointment", appointment);
            return new ModelAndView("/message/create-message");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PreAuthorize("#lessId==#userId or #greaterId==#userId")
    @RequestMapping(value = "/personal/{lessId}&{greaterId}", method = RequestMethod.GET)
    public ModelAndView showPersonaDialog(@PathVariable("lessId") int lessId, @PathVariable("greaterId") int greaterId, @SessionAttribute("userId") int userId, Model model) {
        try {
            Account user = accountService.getById(userId);
            model.addAttribute("user", user);
            Set<Account> dialogs = messageService.getByAppointment(userId, "personal")
                    .stream().map(Message::getSenderId).collect(Collectors.toSet())
                    .stream().map(senderId -> accountService.getById(senderId)).collect(Collectors.toSet());
            model.addAttribute("dialogs", dialogs);
            if (userId != lessId || userId != greaterId) {
                int id = lessId == userId ? greaterId : lessId;
                List<MessageDto> messagePersonal = messageService.getPrivateDialog(userId, id)
                        .stream().map(message -> messageMapper.messageToService(message)).collect(Collectors.toList());
                model.addAttribute("messagePersonal", messagePersonal);
                Account interlocutor = accountService.getById(id);
                model.addAttribute("interlocutor", interlocutor);
            }
            return new ModelAndView("/message/private-messages");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("common/error-page");
        }
    }

    @MessageMapping("/chat/{senderId}&{recipientId}")
    @SendTo("/topic/{senderId}&{recipientId}")
    public Message sendPersonalMessage(Message message) {
        try {
            message.setAppointment("personal");
            message.setTimeSend(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            message.setImg(new byte[0]);
            messageService.create(message);
            return message;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}