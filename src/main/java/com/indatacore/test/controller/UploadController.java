package com.indatacore.test.controller;

import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.User;
import com.indatacore.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/upload")
public class UploadController {
    String uploadDir=System.getProperty("user.home")+"/files/";
    @PostMapping("/files")
    public List<String> uploadFiles(@RequestParam("file") List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();



        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // Ensure the upload directory exists, if not, create it
                    File directory = new File(uploadDir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    // Save the file to the server
                    String filePath = uploadDir + File.separator + (file.getOriginalFilename());
                    File dest = new File(filePath);
                    file.transferTo(dest);

                    // You can also save additional file metadata or perform other operations as needed
                    fileNames.add(file.getOriginalFilename());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
            return null;
        }

        return fileNames;
    }




}
