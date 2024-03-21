package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDto {
    private int id;
    private String name;
    private String description;
    private String members;
    private byte[] img;
    private int idCreator;
    private String creationDate;

}