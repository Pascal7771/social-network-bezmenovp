package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.recipientId = :idRecipient AND m.appointment = :appointment ORDER BY m.timeSend DESC")
    List<Message> getByAppointment(@Param("idRecipient") int idRecipient, @Param("appointment") String appointment);

    @Query(value = "(SELECT * FROM Message WHERE recipientId = :idRecipient AND" +
            " senderId = :senderId AND appointment = :appointment) " +
            "UNION (SELECT * FROM Message WHERE recipientId = :senderId AND" +
            " senderId = :idRecipient AND appointment = :appointment )" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Message> getByRecipientIdAAndSenderIdAndAppointment(@Param("idRecipient") int idRecipient
            , @Param("senderId") int idSender, @Param("appointment") String appointment);

}