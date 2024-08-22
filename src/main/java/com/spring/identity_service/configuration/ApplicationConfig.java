package com.spring.identity_service.configuration;

import com.spring.identity_service.entity.User;
import com.spring.identity_service.enums.Role;
import com.spring.identity_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepo) {
        return args -> {
              if(userRepo.findByUsername("admin").isEmpty()) {
                  var roles = new HashSet<String>();
                  roles.add(Role.ADMIN.name());
                  User admin = User.builder()
                          .username("admin")
                          .password(passwordEncoder.encode("admin"))
                          .roles(roles)
                          .build();

                  userRepo.save(admin);
                  log.warn("Admin user has been created with default username and password: admin");
              }
        };
    }
}
