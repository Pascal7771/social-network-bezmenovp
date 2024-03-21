package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImp implements AccountDao {
    private AccountRepository accountRepository;

    @Autowired
    public AccountRepositoryImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public List<Account> getBySubSrt(String subString) {
        return accountRepository.getBySubSrt(subString);
    }

    public Page<Account> findByTagName(String subString, Pageable page) {
        return accountRepository.findByTagName(subString, page);
    }

    public Account getAccountGroupsList(@Param("id") int id) {
        return accountRepository.getAccountGroupsList(id);
    }

    @Override
    public Account create(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account getById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account update(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public boolean deleteById(int id) {
        accountRepository.deleteById(id);
        return true;
    }

    public void makeAdmin(int id) {
        accountRepository.makeAdmin(id);
    }

}