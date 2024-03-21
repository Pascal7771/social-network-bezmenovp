package com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;

import java.util.List;

public interface AccountDao extends EntityDao<Account> {

    Account getByEmail(String email);

    List<Account> getAll();

}