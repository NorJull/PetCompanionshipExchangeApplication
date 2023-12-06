package com.norjull.petcompanionshipexchange.application.query.owner;

import com.norjull.petcompanionshipexchange.domain.model.Owner;
import com.norjull.petcompanionshipexchange.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createOwner")
    public ResponseEntity<CreateOwnerResponseDTO> createOwner(@RequestBody CreateOwnerDTO dto) {

        Owner owner = Owner.builder().name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
        Owner newOwner = ownerService.saveOwner(owner);

        CreateOwnerResponseDTO createOwnerResponseDTO = CreateOwnerResponseDTO.builder().id(newOwner.getId()).build();

       return ResponseEntity.ok(createOwnerResponseDTO);

    }
}
