package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import org.springframework.stereotype.Service;

@Service
public class GroupValidator extends Throwable {

    public Group validateGroup(Group group) throws ValidateException {
        validateNameGroup(group.getName());
        validateDescriptionGroup(group.getDescription());
        return group;
    }

    private String validateNameGroup(String name) throws ValidateException {
        if (name != null && name.length() < 20) {
            return name;
        } else {
            throw new ValidateException("Name is empty or longer than 20 characters");
        }
    }

    private String validateDescriptionGroup(String description) throws ValidateException {
        if (description != null && description.length() < 40) {
            return description;
        } else {
            throw new ValidateException("Description is empty or longer than 20 characters");
        }
    }

}