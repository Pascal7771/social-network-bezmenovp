package com.getjavajob.training.bezmenovp.socialnetwork.dao.datajpa;

import com.getjavajob.training.bezmenovp.socialnetwork.common.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {

    @Query("SELECT g FROM Group g WHERE g.name like :substring")
    List<Group> getBySubSrt(@Param("substring") String substring);

    @Query("SELECT g FROM Group g WHERE g.name like :substring")
    Page<Group> findByTagName(@Param("substring") String substring, Pageable page);

    @Modifying
    @Query(value = "DELETE FROM GROUP_MEMBER WHERE idGroup =:id", nativeQuery = true)
    void deleteFromAccountGroup(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM GROUP_INVITE WHERE idGroup =:id", nativeQuery = true)
    void deleteFromGroupInvite(@Param("id") int id);

}