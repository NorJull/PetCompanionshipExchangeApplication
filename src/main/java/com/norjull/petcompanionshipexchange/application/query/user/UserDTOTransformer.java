package com.norjull.petcompanionshipexchange.application.query.user;

import com.norjull.petcompanionshipexchange.domain.model.Role;
import com.norjull.petcompanionshipexchange.domain.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTOTransformer {

    public static UserEntity toUserCredentials(UserDTO dto){
        List<Role> roles = new ArrayList<>();
        if (dto.getUserType().equals(Role.OWNER.name()))
            roles.add(Role.OWNER);
        else
            roles.add(Role.PET_LOVER);

        return new UserEntity(null, dto.getUsername(), dto.getEmail(), dto.getPassword(), null);

    }

    public static UserDTO toUserDTO(UserEntity user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                null,
                user.getRoles().contains(Role.OWNER) ? Role.OWNER.name() : Role.PET_LOVER.name());
    }

}
