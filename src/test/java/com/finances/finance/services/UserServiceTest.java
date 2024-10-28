package com.finances.finance.services;

import com.finances.finance.domain.entities.user.UserRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void register() {

        UserRegisterDto userRegisterDto = new UserRegisterDto("Cesar", "cesar.fjunior@telefonica.com", "12345", new Date());

        userService.register(userRegisterDto);

        

    }

    @Test
    void getUserById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }
}