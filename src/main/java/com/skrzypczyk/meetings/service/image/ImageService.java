package com.skrzypczyk.meetings.service.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadEventImage(MultipartFile image, String eventIdentity);
    String getImage(String eventIdentity);
}
