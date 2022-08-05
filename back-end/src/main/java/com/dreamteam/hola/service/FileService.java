package com.dreamteam.hola.service;

import com.dreamteam.hola.util.fileUpload.FileUploadProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Path dirLocation;

    @Autowired
    public FileService(FileUploadProperties fileUploadProperties){
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init(){
        try{
            Files.createDirectories(this.dirLocation);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String saveFile(MultipartFile multipartFile){

        String filename = multipartFile.getOriginalFilename();
        Path location = this.dirLocation.resolve(filename);
        try{
            Files.copy(multipartFile.getInputStream(),location, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
        return filename;
    }


}
