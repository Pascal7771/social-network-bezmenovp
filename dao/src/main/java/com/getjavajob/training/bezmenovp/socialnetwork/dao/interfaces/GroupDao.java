package com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;

import java.util.List;

public interface GroupDao extends EntityDao<Group> {

    List<Group> getAll();

}