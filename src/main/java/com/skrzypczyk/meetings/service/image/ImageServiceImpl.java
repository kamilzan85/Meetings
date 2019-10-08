package com.skrzypczyk.meetings.service.image;

import com.skrzypczyk.meetings.exception.StorageException;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${image.event.path}")
    private String imageUploadPath;

    private final String UPLOAD_ERROR_MSG = "Failed to store file %s";

    @Override
    public void uploadEventImage(MultipartFile image, String eventIdentity) {
        if(!image.isEmpty()){
            String fileExtension = image.getOriginalFilename().split("\\.")[1];
            String fileName = eventIdentity + fileExtension;
            try {
                Files.copy(image.getInputStream(), Paths.get(imageUploadPath + fileName + "." + fileExtension), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new StorageException(String.format(UPLOAD_ERROR_MSG, fileName));
            }
        }
    }

    @Override
    public String getImage(String eventIdentity) {
        File f = new File(imageUploadPath);
        File[] matchingFiles = f.listFiles((dir, name) -> name.startsWith(eventIdentity));
        if(matchingFiles != null && matchingFiles.length>0){
            return "images/events/"+matchingFiles[0].getName();
        }
        return "images/events/no-photo.jpg";
    }
}
