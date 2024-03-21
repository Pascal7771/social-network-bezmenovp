package com.getjavajob.training.bezmenovp.socialnetwork.service;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Account;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util.ValidateException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountValidator extends Throwable {

    public Account validateAccount(Account accountRequest) throws ValidateException {
        validateSurnameAccount(accountRequest.getSurname());
        validateNameAccount(accountRequest.getName());
        validatePatronymicAccount(accountRequest.getPatronymic());
        validateBirthDayAccount(String.valueOf(accountRequest.getBirthDay()));
        validatePhoneAccount(accountRequest.getPhone());
        validateWorkPhoneAccount(accountRequest.getWorkPhone());
        validateAddressAccount(accountRequest.getAddress());
        validateWorkAddressAccount(accountRequest.getWorkAddress());
        validateEmailAccount(accountRequest.getEmail());
        validateIcqAccount(accountRequest.getIcq());
        validateSkypeAccount(accountRequest.getSkype());
        validateAdditionallyAccount(accountRequest.getAdditionally());
        validatePasswordAccount(accountRequest.getPassword());
        return accountRequest;
    }

    private void validateSurnameAccount(String surname) throws ValidateException {
        if (surname == null || surname.length() > 20) {
            throw new ValidateException("Surname is empty or longer than 20 characters");
        }
    }

    private void validateNameAccount(String name) throws ValidateException {
        if (name == null || name.length() > 20) {
            throw new ValidateException("Name longer than 20 characters");
        }
    }

    private void validatePatronymicAccount(String patronymic) throws ValidateException {
        if (patronymic == null || patronymic.length() > 20) {
            throw new ValidateException("Patronymic longer than 20 characters");
        }
    }

    private void validateBirthDayAccount(String birthDayAccount) throws ValidateException {
        if (birthDayAccount.length() > 11) {
            throw new ValidateException("Invalid date format yyyy-mm-dd");
        }
    }

    private void validatePhoneAccount(String phoneAccount) throws ValidateException {
        if (phoneAccount == null || phoneAccount.length() != 10) {
            throw new ValidateException("Invalid phone number format");
        }
    }

    private void validateWorkPhoneAccount(String workPhoneAccount) throws ValidateException {
        if (!Objects.equals(workPhoneAccount, "") && workPhoneAccount.length() != 10) {
            throw new ValidateException("Invalid work phone number format");
        }
    }

    private void validateAddressAccount(String addressAccount) throws ValidateException {
        if (addressAccount.length() > 30) {
            throw new ValidateException("Address longer than 30 characters");
        }
    }

    private void validateWorkAddressAccount(String workAddressAccount) throws ValidateException {
        if (workAddressAccount.length() > 30) {
            throw new ValidateException("Address longer than 30 characters");
        }
    }

    private void validateEmailAccount(String email) throws ValidateException {
        if (email == null || email.length() > 30) {
            throw new ValidateException("Email is empty or email address longer than 30 characters");
        }
    }

    private void validateIcqAccount(String icq) throws ValidateException {
        if (icq.length() > 11) {
            throw new ValidateException("ICQ number longer than 11 characters");
        }
    }

    private void validateSkypeAccount(String skype) throws ValidateException {
        if (skype.length() > 20) {
            throw new ValidateException("Skype name longer than 20 characters");
        }
    }

    private void validateAdditionallyAccount(String additionally) throws ValidateException {
        if (additionally.length() > 100) {
            throw new ValidateException("Too many characters in additionally (max. 100)");
        }
    }

    private void validatePasswordAccount(String password) throws ValidateException {
        if (password == null || password.length() > 80) {
            throw new ValidateException("Password is empty or longer than 10 characters");
        }
    }

}