package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends CrudRepository<Group, Integer> {

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.members WHERE g.id = :idGroup")
    Group getMembers(@Param("idGroup") int idGroup);

    @Modifying
    @Query(value = "INSERT INTO GROUP_INVITE (idinviter, idGroup) VALUES (:idInviter, :idGroup)", nativeQuery = true)
    void invite(@Param("idInviter") int idInviter, @Param("idGroup") int idGroup);

    @Modifying
    @Query(value = "DELETE FROM GROUP_INVITE where idInviter = :idInviter AND idGroup = :idGroup", nativeQuery = true)
    void rejectInvite(@Param("idInviter") int idInviter, @Param("idGroup") int idGroup);

    @Query("SELECT a FROM Account a"
            + " LEFT JOIN FETCH a.groupInvite WHERE a.id = :idAccount")
    Account getOutgoingInvites(@Param("idAccount") int idAccount);

}