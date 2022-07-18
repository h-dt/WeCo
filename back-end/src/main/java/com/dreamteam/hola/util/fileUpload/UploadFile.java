package com.dreamteam.hola.util.fileUpload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {

    private String uploadFileName;  //클라이언트가 업로드한 파일명
    private String storeFileName;   //서버 내부에서 관리하는 파일
}
