package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMemberRepository extends CrudRepository<Account, Integer> {

    @Modifying
    @Query(value = "INSERT INTO INVITES (idinviter, idfriend) VALUES (:idInviter, :idFriend)", nativeQuery = true)
    void invite(@Param("idInviter") int idInviter, @Param("idFriend") int idFriend);

    @Query("SELECT a FROM Account a"
            + " LEFT JOIN FETCH a.accountInvites WHERE a.id = :idAccount")
    Account getInvites(@Param("idAccount") int idAccount);

    @Query("SELECT a FROM Account a"
            + " LEFT JOIN FETCH a.accountOutgoingInvites WHERE a.id = :idAccount")
    Account getOutgoingInvites(@Param("idAccount") int idAccount);

    @Modifying
    @Query(value = "DELETE FROM INVITES where idInviter = :idInviter AND idFriend = :idFriend", nativeQuery = true)
    void rejectInvite(@Param("idInviter") int idInviter, @Param("idFriend") int idAccount);

    @Query("SELECT a FROM Account a"
            + " LEFT JOIN FETCH a.accountFriends WHERE a.id = :id")
    Account getMembers(@Param("id") int idAccount);

}