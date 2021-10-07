package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    void getAdminMailShouldReturnString() {
        //Given

        //When
        String result = adminConfig.getAdminMail();

        //Then
        assertNotNull(result);
    }
}
