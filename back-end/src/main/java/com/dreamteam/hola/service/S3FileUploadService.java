package com.dreamteam.hola.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dreamteam.hola.exception.file.FileNameNullException;
import com.dreamteam.hola.exception.file.NotAllowExtensionException;
import com.dreamteam.hola.exception.file.NotExistException;
import com.dreamteam.hola.util.fileUpload.FileType;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    private String BUCKET;

    @Value("${cloud.aws.s3.bucket.url}")
    private String DEFAULT_URL;

    private final AmazonS3 s3Client;

    public String upload(MultipartFile file) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(date);
        String uuid = UUID.randomUUID().toString();
        String originalName = file.getOriginalFilename();

        log.info("getOriginalFilename() : {}", file.getOriginalFilename());
        log.info("originalName : {}", originalName);

        checkNull(originalName);
        checkExtension(originalName);

        String filename = "profile/" + now + "/" + uuid + "_" + originalName;
        long size = file.getSize();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(size);

        s3Client.putObject(new PutObjectRequest(BUCKET, filename, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));

        return String.valueOf(s3Client.getUrl(BUCKET, filename));
    }

    public void remove(String url) {
        String filename = url.replace(DEFAULT_URL,"");
        log.info("filename : {}", filename);
        String decodeFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8);
        if (!s3Client.doesObjectExist(BUCKET, decodeFilename)) {
            throw new NotExistException("프로필 수정 중 오류가 발생하였습니다.");
        }
        s3Client.deleteObject(BUCKET, decodeFilename);
    }

    private void checkNull(String originalName){
        if(StringUtils.isNullOrEmpty(originalName)){
            throw new FileNameNullException("빈 파일명으론 프로필을 등록할 수 없습니다.");
        }
    }

    private void checkExtension(String originalName){
        String filename = originalName.toUpperCase(Locale.ROOT);
        for (FileType value : FileType.values()) {
            if(filename.endsWith(String.valueOf(value)))
                return;
        }
        throw new NotAllowExtensionException("해당 확장자는 지원하지 않습니다.");
    }
}
