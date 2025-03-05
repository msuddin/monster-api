package com.thetestroom.monster_api.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MonsterController.class)
class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllMonsters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/monsters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); // Assuming 3 monsters are returned
    }
}
