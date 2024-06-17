package com.restapi.controllers;

import com.restapi.entity.Teacher;
import com.restapi.helper.FileUploadHelper;
import com.restapi.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
public class TeacherControllers {
    @Autowired
    TeacherService teacherService;
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @GetMapping("/getDetails")
    public ResponseEntity<?> getAllDetails(){
     List<Teacher> data= teacherService.getAllDetails();
      return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getDetails/{id}")
    public Teacher getDetailsById(@PathVariable("id") int id){
        return teacherService.getDetailsById(id);
    }

    @GetMapping("/getTeacher/{name}")
    public ResponseEntity<?> getAllTeacherByName(@PathVariable("name") String name){
        return teacherService.getTeacherByName(name);

    }
    @PostMapping("/getDataById")
    public ResponseEntity<?> getAllDataById(@RequestBody Teacher teacher){
        return teacherService.getAllIdsData(teacher);
    }

    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/updateTeacher/{id}")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher data,@PathVariable("id") int id){
        return teacherService.updateTeacher(data,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteData(@PathVariable("id") int id){
        teacherService.deleteData(id);
    }


    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Can't be empty.");
        }

//        if(!file.getContentType().equals("image/jpeg")){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Content Must be JPEG");
//        }

        if(fileUploadHelper.uploadFile(file)){
//            return ResponseEntity.ok("File is uploaded Successfully");
            return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, Try again?");


    }
}
