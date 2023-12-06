package com.norjull.petcompanionshipexchange.domain.service;

import com.norjull.petcompanionshipexchange.domain.model.Owner;
import com.norjull.petcompanionshipexchange.domain.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

}
