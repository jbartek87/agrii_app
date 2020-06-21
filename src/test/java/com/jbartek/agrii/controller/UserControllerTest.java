package com.jbartek.agrii.controller;

import com.google.gson.Gson;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.facade.UserFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @MockBean
    UserFacade facade;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationLogsService service;

    @Test
    public void shouldFetchAllUser() throws Exception {
        //Given
        List<UserDto> testList = new ArrayList<>();
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
                "jb@google.pl", " 12345");
        testList.add(userDto);
        when(facade.fetchUsers()).thenReturn(testList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
                "jb@google.pl", "12345");
        long testId = userDto.getId();
        when(facade.fetchUser(testId)).thenReturn(Optional.ofNullable(userDto));
        //When & THen
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Bartek")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.farmNumber", Matchers.is("PL200987")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jb@google.pl")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("12345")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
                "jb@google.pl", "12345");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));
        verify(facade, times(1)).createUser(any(UserDto.class));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
                "jb@google.pl", "12345");
        long testId = userDto.getId();
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facade, times(1)).deleteUser(testId);
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
                "jb@google.pl", "12345");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        when(facade.updateUser(any(UserDto.class))).thenReturn(userDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(facade, times(2)).updateUser(any(UserDto.class));

    }
}