package com.dreamteam.hola.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.dreamteam.hola.exception.file.FileNameNullException;
import com.dreamteam.hola.exception.file.NotAllowExtensionException;
import com.dreamteam.hola.exception.file.NotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


@RequiredArgsConstructor
@Slf4j
@Service
public class S3FileUploadService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    private final AmazonS3 s3Client;

    public String upload(MultipartFile file) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(date);
        String uuid = UUID.randomUUID().toString();

        String originalName = file.getOriginalFilename();

        checkNull(originalName);
        checkExtension(originalName);

        String filename = "profile/" + now + "/" + uuid + "_" + originalName;
        long size = file.getSize();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(size);

        s3Client.putObject(new PutObjectRequest(bucket, filename, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        String imagePath = s3Client.getUrl(bucket, filename).toString();

        return imagePath;
    }

    public void remove(String url) {
        String filename = url.replace(defaultUrl,"");
        log.info("filename : {}", filename);
        if (!s3Client.doesObjectExist(bucket, filename)) {
            throw new NotExistException("프로필 수정 중 오류가 발생하였습니다.");
        }
        s3Client.deleteObject(bucket, filename);
    }

    public void checkNull(String originalName){
        if(originalName == null){
            throw new FileNameNullException("빈 파일명으론 프로필을 등록할 수 없습니다.");
        }
    }

    public void checkExtension(String originalName){
        String filename = originalName.toLowerCase(Locale.ROOT);
        if(!(filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg") || filename.endsWith(".raw") || filename.endsWith(".tiff") || filename.endsWith(".bmp"))){
            throw new NotAllowExtensionException("해당 확장자는 지원하지 않습니다.");
        }
    }
}
