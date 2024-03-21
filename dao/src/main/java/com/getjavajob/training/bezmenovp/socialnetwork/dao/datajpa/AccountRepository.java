package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.name LIKE :substring")
    List<Account> getBySubSrt(@Param("substring") String substring);

    @Query("SELECT a FROM Account a WHERE a.name like :substring")
    Page<Account> findByTagName(@Param("substring") String substring, Pageable page);

    @Query("SELECT a FROM Account a"
            + " LEFT JOIN FETCH a.accountGroup WHERE a.id = :id")
    Account getAccountGroupsList(@Param("id") int id);

    Account findByEmail(String email);

    @Modifying
    @Query("UPDATE Account a SET a.role='ADMIN' WHERE a.id= :id")
    void makeAdmin(@Param("id") int id);

}