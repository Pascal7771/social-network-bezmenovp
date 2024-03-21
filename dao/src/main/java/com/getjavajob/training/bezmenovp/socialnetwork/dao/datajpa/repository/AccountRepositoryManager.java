package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.repository;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa.AccountRepository;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.AccountDto;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

@AllArgsConstructor
@Repository
public class AccountRepositoryManager {
    private AccountRepository accountRepository;
    private AccountMapper mapper;

    public boolean deleteFriend(int accountId, int friendId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        Account accountFriend = accountRepository.findById(friendId).orElse(null);
        account.getAccountFriends().remove(accountFriend);
        accountFriend.getAccountFriends().remove(account);
        accountRepository.save(account);
        accountRepository.save(accountFriend);
        return true;
    }

    public boolean acceptInvite(int accountId, int friendId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        Account accountFriend = accountRepository.findById(friendId).orElse(null);
        account.getAccountInvites().remove(accountFriend);
        account.getAccountFriends().add(accountFriend);
        accountFriend.getAccountOutgoingInvites().remove(account);
        accountFriend.getAccountFriends().add(account);
        accountRepository.save(account);
        accountRepository.save(accountFriend);
        return true;
    }

    public void convertToXml(int id) throws JAXBException {
        Account account = accountRepository.findById(id).orElse(null);
        AccountDto accountDto = mapper.accountToService(account);
        JAXBContext jaxbContext = JAXBContext.newInstance(AccountDto.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(accountDto, new File("D:/xml/account" + id + ".xml"));
    }

    public Account convertFromXml(int id, InputStream fileInputStream) throws JAXBException {
        Account account = accountRepository.findById(id).orElse(null);
        JAXBContext jaxbContext = JAXBContext.newInstance(AccountDto.class);
        Unmarshaller un = jaxbContext.createUnmarshaller();
        AccountDto accountFromXml = (AccountDto) un.unmarshal(fileInputStream);
        return mapFromXml(account, accountFromXml);
    }

    private Account mapFromXml(Account account, AccountDto accountFromXml) {
        account.setName(accountFromXml.getName());
        account.setSurname(accountFromXml.getSurname());
        account.setPatronymic(accountFromXml.getPatronymic());
        account.setAddress(accountFromXml.getAddress());
        account.setWorkAddress(accountFromXml.getWorkAddress());
        account.setPhone(accountFromXml.getPhone());
        account.setWorkPhone(accountFromXml.getWorkPhone());
        account.setIcq(accountFromXml.getIcq());
        account.setAdditionally(accountFromXml.getAdditionally());
        return account;
    }

}