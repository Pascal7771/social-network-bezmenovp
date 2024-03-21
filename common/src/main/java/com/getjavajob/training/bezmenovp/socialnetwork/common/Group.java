package com.getjavajob.training.bezmenovp.socialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "group_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private byte[] img;
    private int idCreator;
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "accountGroup")
    private List<Account> members;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groupInvite")
    private List<Account> inviter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return idCreator == group.idCreator && Objects.equals(name, group.name) && Objects.equals(description, group.description) && Objects.equals(creationDate, group.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, idCreator, creationDate);
    }

}