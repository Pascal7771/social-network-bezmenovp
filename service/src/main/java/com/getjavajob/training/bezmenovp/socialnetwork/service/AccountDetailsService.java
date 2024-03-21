package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccountDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private AccountService accountService;

    @Autowired
    public AccountDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Account account = accountService.getById(accountService.getByEmail(email).getId());
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(account.getRole().toString()));
            return new User(account.getEmail(), account.getPassword(), roles);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Пользователь с таким email не найден");
        }
    }

}