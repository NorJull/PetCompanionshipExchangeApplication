package com.norjull.petcompanionshipexchange.domain.service;

import java.util.Date;

public interface PetStorageService {
    default String uploadPhoto(String imageName, byte[] inputStream, long contentLength) {
        return "default-url" + new Date().getTime();
    }
}
