package com.getjavajob.training.bezmenovp.socialnetwork.dao.interfaces;

public interface EntityDao<T> {

    T create(T entity);

    T getById(int id);

    T update(T entity);

    boolean deleteById(int id);

}