/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isconseil.test_api.controllers;

import com.isconseil.test_api.models.ERole;
import com.isconseil.test_api.models.User;
import com.isconseil.test_api.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Nolhan
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

    @Autowired
    private UserRepository userService;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {

        User user = User.builder()
                .username("nolhan")
                .phoneNumber("96324774")
                .email("nolhan@nolhan.com")
                .userBirthday(new Date())
                .country("Togo")
                .password("12345")
                .userRole(ERole.ADMIN)
                .build();

        userService.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }
    
    @Test
    @Order(2)
    public void getListOfUsersTest(){

        List<User> users = userService.findAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);

    }
}
