package com.getjavajob.training.bezmenovp.socialnetwork.webapp.controller;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.GroupDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.GroupMapper;
import com.getjavajob.training.bezmenovp.socialnetwork.service.AccountService;
import com.getjavajob.training.bezmenovp.socialnetwork.service.GroupService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/search/substr")
public class SearchController {
    private static final Logger logger = LogManager.getLogger();

    private AccountService accountService;
    private GroupService serviceGroup;
    private AccountMapper accountMapper;
    private GroupMapper groupMapper;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.setDisallowedFields("file");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @GetMapping("/{page}")
    public ModelAndView subStrSearch(@PathVariable("page") String page, @RequestParam("substr") String substr, Model model) {
        try {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
            Page<Group> groupSubStr = serviceGroup.getBySubStr("%" + substr + "%", pageable);
            Page<Account> accountSubStr = accountService.getBySubStr("%" + substr + "%", pageable);
            model.addAttribute("accountList", accountSubStr.getContent());
            model.addAttribute("groupList", groupSubStr.getContent());
            model.addAttribute("accountCount", accountSubStr.getTotalPages());
            model.addAttribute("groupCount", groupSubStr.getTotalPages());
            return new ModelAndView("/common/search-substring");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ModelAndView("/common/error-page");
        }
    }

    @GetMapping()
    @ResponseBody
    public List<Object> subStrAutocompleteGroup(@RequestParam("search") String substr) {
        List<GroupDto> groupsSearch = serviceGroup.getBySubStr(substr)
                .stream().map(group -> groupMapper.groupToService(group)).collect(Collectors.toList());
        List<AccountDto> accountDtoSearch = accountService.getBySubStr("%" + substr + "%")
                .stream().map(account -> accountMapper.accountToService(account)).collect(Collectors.toList());
        List<Object> allSearch = new ArrayList<>(accountDtoSearch);
        allSearch.addAll(groupsSearch);
        return allSearch;
    }

}