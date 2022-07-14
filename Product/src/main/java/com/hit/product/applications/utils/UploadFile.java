package com.hit.product.applications.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hit.product.configs.exceptions.UploadFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class UploadFile {

    @Autowired
    private Cloudinary cloudinary;

    public String getUrlFromFile(MultipartFile multipartFile) {
        try {
            Map<?, ?> map = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            return map.get("secure_url").toString();
        } catch (IOException e) {
            throw new UploadFileException("Upload file failed");
        }
    }

    public void removeFileFromUrl(String url) {
        try {
            cloudinary.uploader().destroy(url, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new UploadFileException("Destroy file failed");
        }
    }

//    public static File convertMultipartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write( file.getBytes() );
//        fos.close();
//        return convFile;
//    }
}
