package com.example.remtel.repos;

import com.example.remtel.beans.Form;
import com.example.remtel.beans.User;
import com.example.remtel.beans.dto.FormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface FormRepo extends CrudRepository<Form, Long> {
    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :user then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.admitted = false" +
            " group by f")
    Page<FormDto> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :user then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.subject = :subject and" +
            " f.admitted = false" +
            " group by f")
    Page<FormDto> findByNotAdmittedSubject(@Param("subject") String subject, Pageable pageable, @Param("user") User user);
    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :user then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.subject = :subject and " +
            " f.admitted = true" +
            " group by f")
    Page<FormDto> findByAdmittedSubject(@Param("subject") String subject, Pageable pageable, @Param("user") User user);



    //    @Query("from Form f where f.user = :user ")
    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :currentUser then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.user = :user and" +
            " f.admitted = true" +
            " group by f")
    Page<FormDto> findByAdmittedUser(Pageable pageable, @Param("currentUser") User currentUser, @Param("user") User user);
    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :currentUser then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.user = :user and" +
            " f.admitted = false" +
            " group by f")
    Page<FormDto> findByNotAdmittedUser(Pageable pageable, @Param("currentUser") User currentUser, @Param("user") User user);
//    Page<FormDto> findByAdmitted(boolean admitted, Pageable pageable);

    //Page<FormDto> findBy
//    @Query("select new com.example.remtel.beans.dto.FormDto(" +
//            " f, " +
//            " count(fa)," +
//            " sum(case when fa = :user then 1 else 0 end) > 0" +
//            ") " +
//            " from Form f inner join f.admits fa" +
//            " where fa.id = :user" +
//            " group by fa")
    @Query("select new com.example.remtel.beans.dto.FormDto(" +
            " f, " +
            " count(fa)," +
            " sum(case when fa = :user then 1 else 0 end) > 0" +
            ") " +
            " from Form f left join f.admits fa" +
            " where f.admitted = true" +
            " group by f")
    Page<FormDto> findFormsByMeAdmitted(Pageable pageable, @Param("user") User user);

    @Override
    void delete(Form form);
}
