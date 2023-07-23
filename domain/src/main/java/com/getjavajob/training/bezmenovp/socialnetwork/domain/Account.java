package com.getjavajob.training.bezmenovp.socialnetwork.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Account {

    private int accountID;
    private String accountPassword;
    private String accountSurname;
    private String accountName;
    private String accountPatronymic;
    private LocalDate accountBirthDay;
    private String accountPhone;
    private String accountWorkPhone;
    private String accountHomeAddress;
    private String accountBusinessAddress;
    private String accountEmail;
    private String accountICQ;
    private String accountSkype;
    private String accountAdditionalInformation;

    public Account() {
    }

    public Account(String accountPassword, String accountSurname,
                   String accountName, String accountPatronymic, String accountEmail) {
        this.accountPassword = accountPassword;
        this.accountSurname = accountSurname;
        this.accountName = accountName;
        this.accountPatronymic = accountPatronymic;
        this.accountEmail = accountEmail;
    }

    public Account(int accountID, String accountPassword, String accountSurname, String accountName,
                   String accountPatronymic, LocalDate accountBirthDay, String accountHomeAddress,
                   String accountBusinessAddress, String accountEmail, String accountICQ,
                   String accountSkype, String accountAdditionalInformation) {
        this.accountID = accountID;
        this.accountPassword = accountPassword;
        this.accountSurname = accountSurname;
        this.accountName = accountName;
        this.accountPatronymic = accountPatronymic;
        this.accountBirthDay = accountBirthDay;
        this.accountHomeAddress = accountHomeAddress;
        this.accountBusinessAddress = accountBusinessAddress;
        this.accountEmail = accountEmail;
        this.accountICQ = accountICQ;
        this.accountSkype = accountSkype;
        this.accountAdditionalInformation = accountAdditionalInformation;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountSurname() {
        return accountSurname;
    }

    public void setAccountSurname(String accountSurname) {
        this.accountSurname = accountSurname;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPatronymic() {
        return accountPatronymic;
    }

    public void setAccountPatronymic(String accountPatronymic) {
        this.accountPatronymic = accountPatronymic;
    }

    public LocalDate getAccountBirthDay() {
        return accountBirthDay;
    }

    public void setAccountBirthDay(LocalDate accountBirthDay) {
        this.accountBirthDay = accountBirthDay;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getAccountWorkPhone() {
        return accountWorkPhone;
    }

    public void setAccountWorkPhone(String accountWorkPhone) {
        this.accountWorkPhone = accountWorkPhone;
    }

    public String getAccountHomeAddress() {
        return accountHomeAddress;
    }

    public void setAccountHomeAddress(String accountHomeAddress) {
        this.accountHomeAddress = accountHomeAddress;
    }

    public String getAccountBusinessAddress() {
        return accountBusinessAddress;
    }

    public void setAccountBusinessAddress(String accountBusinessAddress) {
        this.accountBusinessAddress = accountBusinessAddress;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountICQ() {
        return accountICQ;
    }

    public void setAccountICQ(String accountICQ) {
        this.accountICQ = accountICQ;
    }

    public String getAccountSkype() {
        return accountSkype;
    }

    public void setAccountSkype(String accountSkype) {
        this.accountSkype = accountSkype;
    }

    public String getAccountAdditionalInformation() {
        return accountAdditionalInformation;
    }

    public void setAccountAdditionalInformation(String accountAdditionalInformation) {
        this.accountAdditionalInformation = accountAdditionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccountID() == account.getAccountID() &&
                Objects.equals(getAccountPassword(), account.getAccountPassword()) &&
                Objects.equals(getAccountSurname(), account.getAccountSurname()) &&
                Objects.equals(getAccountName(), account.getAccountName()) &&
                Objects.equals(getAccountPatronymic(), account.getAccountPatronymic()) &&
                Objects.equals(getAccountBirthDay(), account.getAccountBirthDay()) &&
                Objects.equals(getAccountPhone(), account.getAccountPhone()) &&
                Objects.equals(getAccountWorkPhone(), account.getAccountWorkPhone()) &&
                Objects.equals(getAccountHomeAddress(), account.getAccountHomeAddress()) &&
                Objects.equals(getAccountBusinessAddress(), account.getAccountBusinessAddress()) &&
                Objects.equals(getAccountEmail(), account.getAccountEmail()) &&
                Objects.equals(getAccountICQ(), account.getAccountICQ()) &&
                Objects.equals(getAccountSkype(), account.getAccountSkype()) &&
                Objects.equals(getAccountAdditionalInformation(), account.getAccountAdditionalInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountID(), getAccountPassword(), getAccountSurname(), getAccountName(),
                getAccountPatronymic(), getAccountBirthDay(), getAccountPhone(), getAccountWorkPhone(),
                getAccountHomeAddress(), getAccountBusinessAddress(), getAccountEmail(), getAccountICQ(),
                getAccountSkype(), getAccountAdditionalInformation());
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountSurname='" + accountSurname + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountPatronymic='" + accountPatronymic + '\'' +
                ", accountBirthDay=" + accountBirthDay +
                ", accountPhone='" + accountPhone + '\'' +
                ", accountWorkPhone='" + accountWorkPhone + '\'' +
                ", accountHomeAddress='" + accountHomeAddress + '\'' +
                ", accountBusinessAddress='" + accountBusinessAddress + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountICQ='" + accountICQ + '\'' +
                ", accountSkype='" + accountSkype + '\'' +
                ", accountAdditionalInformation='" + accountAdditionalInformation + '\'' +
                '}';
    }

}