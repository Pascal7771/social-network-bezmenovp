package com.getjavajob.training.bezmenovp.socialnetwork.common;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "account")
@SecondaryTables({
        @SecondaryTable(name = "phones", pkJoinColumns = @PrimaryKeyJoinColumn(name = "accountId"))
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "Account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthDay;
    @Column(table = "phones")
    private String phone;
    @Column(table = "phones")
    private String workPhone;
    private String address;
    private String workAddress;
    private String email;
    private String icq;
    private String skype;
    private String additionally;
    private String password;
    @XmlTransient
    private byte[] img;
    private LocalDate creationDate;
    @XmlTransient
    @Enumerated(EnumType.STRING)
    private Roles role;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "invites", joinColumns = @JoinColumn(name = "idInviter"), inverseJoinColumns = @JoinColumn(name = "idFriend"))
    private List<Account> accountOutgoingInvites;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "invites", joinColumns = @JoinColumn(name = "idFriend"), inverseJoinColumns = @JoinColumn(name = "idInviter"))
    private List<Account> accountInvites;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "idAccount"), inverseJoinColumns = @JoinColumn(name = "idFriend"))
    private List<Account> accountFriends;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_member", joinColumns = @JoinColumn(name = "idMember"), inverseJoinColumns = @JoinColumn(name = "idGroup"))
    private Set<Group> accountGroup;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "group_invite", joinColumns = @JoinColumn(name = "idInviter"), inverseJoinColumns = @JoinColumn(name = "idGroup"))
    private List<Group> groupInvite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(surname, account.surname) && Objects.equals(name, account.name) && Objects.equals(patronymic, account.patronymic) && Objects.equals(birthDay, account.birthDay) && Objects.equals(phone, account.phone) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, birthDay, phone, email);
    }

}