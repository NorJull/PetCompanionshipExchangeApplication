package com.norjull.petcompanionshipexchange.domain.service;

import com.norjull.petcompanionshipexchange.domain.model.UserEntity;
import com.norjull.petcompanionshipexchange.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);

    }

    // Additional methods for user-related functionality
}
