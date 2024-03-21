package com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.util;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import com.getjavajob.training.bezmenovp.socialnetwork.dao.dto.GroupDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

@AllArgsConstructor
@Component
public class GroupMapper {
    private Helper helper;

    public Group groupToData(GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .name(groupDto.getName())
                .description(groupDto.getDescription())
                .img(groupDto.getImg())
                .idCreator(groupDto.getIdCreator())
                .creationDate(groupDto.getCreationDate() == null ? parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        : parse(groupDto.getCreationDate()))
                .build();
    }

    public Group groupToData(Group group, GroupDto groupDto) {
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        group.setImg(groupDto.getImg());
        group.setIdCreator(group.getIdCreator());
        group.setCreationDate(groupDto.getCreationDate() == null ? parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                : parse(groupDto.getCreationDate()));
        return group;
    }

    public GroupDto groupToService(Group groupData) {
        return GroupDto.builder()
                .id(groupData.getId())
                .name(groupData.getName())
                .description(groupData.getDescription())
                .img(helper.printAvatar(groupData.getImg()))
                .idCreator(groupData.getIdCreator())
                .creationDate((groupData.getCreationDate().toString()))
                .build();
    }

}