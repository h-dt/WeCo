package com.dreamteam.hola.util.fileUpload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.upload.location}")
    private String fileDir;

    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException{
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));;

        return new UploadFile(originalFilename,storeFileName);
    }

    private String createStoreFileName(String originalFileName){
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String storeFileName = uuid +"."+ext;
        return storeFileName;
    }

    private String extractExt(String originalName){
        int pos = originalName.lastIndexOf(".");
        String ext = originalName.substring(pos + 1);
        return  ext;
    }
}
