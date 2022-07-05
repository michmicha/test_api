/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.isconseil.test_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isconseil.test_api.models.User;
import com.isconseil.test_api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 *
 * @author Nolhan
 */
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userService;

    public UserControllerTest() {
    }

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {

        User user = User.builder()
                .username("nolhan")
                .phoneNumber("96324774")
                .email("nolhan@gmail.com")
                .userBirthday(new Date())
                .country("Togo")
                .password("12345")
                .build();
        given(userService.save(any(User.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.username",
                        is(user.getUsername())))
                .andExpect(jsonPath("$.phoneNumber",
                        is(user.getPhoneNumber())))
                .andExpect(jsonPath("$.email",
                        is(user.getEmail())))
                .andExpect(jsonPath("$.userBirthday",
                        is(user.getUserBirthday())))
                .andExpect(jsonPath("$.country",
                        is(user.getCountry())))
                .andExpect(jsonPath("$.password",
                        is(user.getPassword())));

    }

    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception {
        // given - precondition or setup
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(User.builder().username("nolhan").phoneNumber("96324774").email("nolhan@gmail.com").userBirthday(new Date()).country("Togo").password("12345").build());
        listOfUsers.add(User.builder().username("yayra").phoneNumber("90782354").email("yayra@gmail.com").userBirthday(new Date()).country("Benin").password("12345").build());

        given(userService.findAll()).willReturn(listOfUsers);

        ResultActions response = mockMvc.perform(get("/api/users"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsers.size())));

    }

}
