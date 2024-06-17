package com.restapi.repository;

import com.restapi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
    @Query(
            value = "select * from Teacher where name=?1",nativeQuery = true
    )
    public List<Teacher> getAllTeacherByName(String name);

    public List<Teacher> findByNameIgnoreCase(String name);

    @Query(
            value="select * from teacher where id in:ids",nativeQuery = true
    )
    public List<Teacher> getAllDataByListOfIds(List<Integer> ids);
}
