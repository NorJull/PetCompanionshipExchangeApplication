package com.norjull.petcompanionshipexchange;

import com.norjull.petcompanionshipexchange.domain.model.Role;
import com.norjull.petcompanionshipexchange.domain.model.RoleEntity;
import com.norjull.petcompanionshipexchange.domain.model.UserEntity;
import com.norjull.petcompanionshipexchange.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@EntityScan("com.norjull.petcompanionshipexchange.domain.model")
@EnableJpaRepositories
@SpringBootApplication
public class PetCompanionshipExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetCompanionshipExchangeApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("naren@mail.com")
					.username("naren")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(Role.valueOf(Role.OWNER.name()))
							.build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("david@mail.com")
					.username("david")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(Role.valueOf(Role.PET_LOVER.name()))
							.build()))
					.build();


			userRepository.save(userEntity);
			userRepository.save(userEntity2);
		};
	}
}
