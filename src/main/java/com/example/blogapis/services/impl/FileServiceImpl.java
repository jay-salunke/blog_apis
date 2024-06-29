package com.example.blogapis.services.impl;


import com.example.blogapis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        /* steps */

        // 1. getFileName
        String name = file.getOriginalFilename();

        //concating randomUUID with fileName
        String newFileName = UUID.randomUUID().toString()+name.substring(name.lastIndexOf("."));


        //2. Create new fullPath
        String filePath = path+ File.separator+newFileName;

        //3.Create Folder if not created in given path
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //4. Upload the file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return newFileName;


    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path+File.separator+fileName;
        return new FileInputStream(fullPath);

    }
}
