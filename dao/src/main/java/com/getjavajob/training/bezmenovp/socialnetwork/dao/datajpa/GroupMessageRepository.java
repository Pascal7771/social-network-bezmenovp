package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.MessageGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupMessageRepository extends CrudRepository<MessageGroup, Integer> {

    @Query("SELECT mg FROM MessageGroup mg"
            + " WHERE mg.recipientId =:idRecipient AND mg.appointment =:appointment"
            + " ORDER BY mg.timeSend DESC")
    List<MessageGroup> getByAppointment(@Param("idRecipient") int idRecipient, @Param("appointment") String appointment);

}