package com.norjull.petcompanionshipexchange.domain.service;

import com.norjull.petcompanionshipexchange.domain.model.Image;
import com.norjull.petcompanionshipexchange.domain.model.Owner;
import com.norjull.petcompanionshipexchange.domain.model.Pet;
import com.norjull.petcompanionshipexchange.domain.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private PetStorageService petStorageService;

    @Autowired
    private OwnerRepository ownerRepository;

    public void saveOwner(Owner owner) {

        // Save pets pictures String imageName
        List<Pet> pets = owner.getPets().stream().map(pet -> {
            if (pet.getImages().isEmpty()) {
                return pet;
            }
            Image image = pet.getImages().get(0);
            String profileURL = petStorageService.uploadPhoto(image.getName(), image.getData(), image.getContentLength());
            pet.setProfileImageURL(profileURL);

            return pet;
        }).toList();

        owner.setPets(pets);

        ownerRepository.save(owner);
    }

}
