package com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;

import java.util.List;

public interface MemberDao {

    boolean acceptInvite(int idAccount, int idAppointment);

    Object getMembers(int idAccount);

    boolean delete(int idAccount, int idAppointment);

    void invite(int idInviter, int idAppointment);

    List<Account> getInvitesAccount(int id);

    void rejectInvite(int idInviter, int idAppointment);

}