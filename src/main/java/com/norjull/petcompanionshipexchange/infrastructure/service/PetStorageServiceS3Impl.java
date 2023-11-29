package com.norjull.petcompanionshipexchange.infrastructure.service;

import com.norjull.petcompanionshipexchange.domain.service.PetStorageService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class PetStorageServiceS3Impl implements PetStorageService {

    @Override
    public String uploadPhoto(String imageName, byte[] inputStream, long contentLength) {
        return PetStorageService.super.uploadPhoto(imageName, inputStream, contentLength);
    }
}
