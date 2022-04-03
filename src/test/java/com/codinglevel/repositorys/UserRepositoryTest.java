package com.codinglevel.repositorys;

import com.codinglevel.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void registerUser() {
        User user = User.builder()
                .firstName("Mohamed")
                .lastName("Almousleck")
                .email("mohamed@gmail.com")
                .department("Medicine")
                .build();

        userRepository.save(user);
    }
}