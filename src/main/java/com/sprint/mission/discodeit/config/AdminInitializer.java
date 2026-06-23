package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  
  @Override
  public void run(ApplicationArguments args) {

    if (!userRepository.existsByRole(User.Role.ADMIN)) {

      String username = "admin";
      String email = "admin@discodeit.com";
      String password = "1234";

      User admin = User.create(username, email, passwordEncoder.encode(password));

      admin.updateRole(User.Role.ADMIN);

      userRepository.save(admin);
    }
  }
}
