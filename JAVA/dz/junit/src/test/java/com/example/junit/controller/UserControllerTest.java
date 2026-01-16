package com.example.junit.controller;

import com.example.junit.entities.User;
import com.example.junit.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDb() {
        userRepository.deleteAll();
    }

    @Test
    void getAllUsersTest() throws Exception {
        User user = new User();
        user.setFirstName("111");
        user.setLastName("222");
        user.setEmail("dff@mail.ru");
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getUserByIdExistingTest() throws Exception {
        User user = new User();
        user.setFirstName("111");
        user.setLastName("222");
        user.setEmail("dff@mail.ru");
        User savedUser = userRepository.save(user);

        Long id = savedUser.getId();

        mockMvc.perform(get("/api/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value(savedUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(savedUser.getLastName()))
                .andExpect(jsonPath("$.email").value(savedUser.getEmail()));
    }

    @Test
    void getUserByIdNotExistingTest() throws Exception {
        mockMvc.perform(get("/api/users/{id}", 999))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createUserTest() throws Exception {
        String json = """
                {
                "firstName": "111",
                "lastName": "222",
                "email": "eee@mail.ru"
                }
                """;

        MvcResult result = mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String location = result.getResponse().getHeader("Location");

        mockMvc.perform(get(location))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("111"))
                .andExpect(jsonPath("$.lastName").value("222"))
                .andExpect(jsonPath("$.email").value("eee@mail.ru"));
    }

    @Test
    void createUserInvalidDataTest() throws Exception {
        String json = """
                {
                "firstName": "",
                "lastName": "",
                "email": "fff-aaa"
                }
                """;

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserTest() throws Exception {
        User user = new User();
        user.setFirstName("111");
        user.setLastName("222");
        user.setEmail("111@mail.ru");
        userRepository.save(user);
        Long id = user.getId();

        String json = """
                {
                "firstName": "aaa",
                "lastName": "bbb",
                "email": "aaa@mail.ru"
                }
                """;

        mockMvc.perform(put("/api/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value("aaa"))
                .andExpect(jsonPath("$.lastName").value("bbb"))
                .andExpect(jsonPath("$.email").value("aaa@mail.ru"));
    }

}
