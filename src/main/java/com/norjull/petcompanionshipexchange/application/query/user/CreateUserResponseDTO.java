package com.norjull.petcompanionshipexchange.application.query.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseDTO {

    private String username;

    private String email;

    private Set<String> roles;

    private String token;

}
