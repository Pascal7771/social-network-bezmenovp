package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.MessageDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.MessageMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import com.getjavajob.training.bezmenovp.socialnetwork.service.GroupMessageService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
@RequestMapping("/group/message")
public class GroupMessageController {
    private static final Logger logger = LogManager.getLogger();

    GroupMessageService groupMessageService;
    MessageMapper messageMapper;

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
            return new ModelAndView("/message/create-group-message");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MessageDto messageRequest) throws ValidateException {
        try {
            MessageGroup message = messageMapper.messageGroupToData(messageRequest);
            MessageGroup messageData = groupMessageService.create(message);
            return "redirect:/group/info?id=" + messageData.getRecipientId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "redirect:/error";
        }
    }

    @GetMapping("/img")
    public void printAvatar(@RequestParam("id") int id, HttpServletResponse resp) {
        try {
            MessageGroup messageData = groupMessageService.getById(id);
            MessageDto message = messageMapper.messageGroupToService(messageData);
            resp.getOutputStream().write(message.getImg());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

        }
    }

}