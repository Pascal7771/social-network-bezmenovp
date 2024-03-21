package com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces;

import java.util.List;

public interface MessageDao<T> extends EntityDao<T> {

    List<T> getByAppointment(int idRecipient, String appointment);

}