package com.restapi.services;

import com.restapi.entity.Teacher;
import com.restapi.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    /**
     *
     * Get Data
     */
    public List<Teacher> getAllDetails(){
        List<Teacher> data = teacherRepo.findAll();
        return data;
    }

    /**
     * Get Data By Id
     */
    public Teacher getDetailsById(int id){
        Optional<Teacher> data = teacherRepo.findById(id);
        Teacher teacher = data.get();
        return teacher;
    }


    /**
     * Create Teacher
     */

    public ResponseEntity<?> createTeacher(Teacher teacher){
        teacherRepo.save(teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update Data
     */
    public ResponseEntity<?> updateTeacher(Teacher teacher,int id){
        Optional<Teacher> optional = teacherRepo.findById(id);
        Teacher teacherData=optional.get();
        teacherData.setName(teacher.getName());
        teacherData.setDept(teacher.getDept());
        teacherRepo.save(teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete Data
     */
    public void deleteData(int id){
        teacherRepo.deleteById(id);
    }

    /**
     * Get Tecaher By Name
     */
    public ResponseEntity<?> getTeacherByName(String name){
//         List<Teacher> teacher=teacherRepo.getAllTeacherByName(name);
//         return new ResponseEntity<>(teacher,HttpStatus.OK);

      //Get data from the database using Ignore case
        List<Teacher> teacher = teacherRepo.findByNameIgnoreCase(name);
        return new ResponseEntity<>(teacher,HttpStatus.OK);


    }

    /**
     * Get All Data According to List Of Ids
     */
    public ResponseEntity<?> getAllIdsData(Teacher teacher){
        List<Teacher> allDataByListOfIds = teacherRepo.getAllDataByListOfIds(teacher.getIdsList());
        return new ResponseEntity<>(allDataByListOfIds,HttpStatus.OK);
    }
}
