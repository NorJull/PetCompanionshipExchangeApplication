package com.norjull.petcompanionshipexchange.application.query.user;

import com.norjull.petcompanionshipexchange.application.config.jwt.JwtUtils;
import com.norjull.petcompanionshipexchange.domain.model.Role;
import com.norjull.petcompanionshipexchange.domain.model.RoleEntity;
import com.norjull.petcompanionshipexchange.domain.model.UserEntity;
import com.norjull.petcompanionshipexchange.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        UserEntity user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createUser")
    public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(Role.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userService.registerUser(userEntity);

        //If everything when well add token to the response
        Set<String> stringRoles = userEntity.getRoles().stream()
                        .map(rol -> rol.getName().name()).collect(Collectors.toSet());
        String token = jwtUtils.generateAccesToken(userEntity.getUsername());

        CreateUserResponseDTO createUserResponseDTO = CreateUserResponseDTO.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(stringRoles)
                .token(token).build();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return new ResponseEntity<CreateUserResponseDTO>(createUserResponseDTO, responseHeaders, HttpStatus.CREATED);
    }

}