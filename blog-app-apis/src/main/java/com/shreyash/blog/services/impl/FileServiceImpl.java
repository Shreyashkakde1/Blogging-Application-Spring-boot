package com.shreyash.blog.services.impl;

import com.shreyash.blog.services.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String name = file.getOriginalFilename();

        // Random Name Generate File
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(name.substring(name.indexOf(".")));

        // Full Path
        String filePath = path + File.separator + fileName1;

        // Create Folder if not Created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return null;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);

        return null;
    }
}
