package com.restapi.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public  String upload_dir="";
//    public final String upload_dir= "C:\\Users\\admin.nile\\Documents\\RestExample\\src\\main\\resources\\static\\image";



    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file) throws IOException {

        boolean f=false;
        try{
            System.out.println("Path::::"+new ClassPathResource("static/image").getFile().getAbsolutePath());
            upload_dir=new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(upload_dir+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
