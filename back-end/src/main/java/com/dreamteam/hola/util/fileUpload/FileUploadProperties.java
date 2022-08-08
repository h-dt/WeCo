package com.dreamteam.hola.util.fileUpload;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FileUploadProperties {

    @Value("${file.upload.location}")
    private String location;


}
