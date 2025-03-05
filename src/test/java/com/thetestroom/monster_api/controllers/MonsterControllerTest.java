package com.thetestroom.monster_api.controllers;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.repositories.MonsterRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@WebMvcTest(MonsterController.class)
public class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonsterRepository monsterRepository;

    @Test
    void testGetAllMonsters() throws Exception {
        Monster monster1 = new Monster(1L, "Monster1", "Type1");
        Monster monster2 = new Monster(2L, "Monster2", "Type2");
        Monster monster3 = new Monster(3L, "Monster3", "Type3");
        given(monsterRepository.findAll()).willReturn(Arrays.asList(monster1, monster2, monster3));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/monsters"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)) // Expect 3 monsters
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Monster1")) // Check first monster's name
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Monster2")) // Check second monster's name
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Monster3")); // Check third monster's name
    }

    @Test
    void testGetMonsterById() throws Exception {
        Monster monster = new Monster(1L, "Monster1", "Type1");
        given(monsterRepository.findById(1L)).willReturn(Optional.of(monster));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/monsters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Monster1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Type1"));
    }

    @Test
    void testGetMonsterByIdNotFound() throws Exception {
        given(monsterRepository.findById(99L)).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/monsters/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // Expect 404 NOT FOUND
    }

    @Test
    @Disabled
    void testCreateMonster() throws Exception {
        Monster monster = new Monster(1L, "Monster1", "Type1");
        given(monsterRepository.save(monster)).willReturn(monster);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/monsters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Monster1\",\"type\":\"Type1\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated()) // Expect 201 CREATED
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Monster1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Type1"));
    }

    @Test
    void testUpdateMonster() throws Exception {
        Monster existingMonster = new Monster(1L, "Monster1", "Type1");
        Monster updatedMonster = new Monster(1L, "UpdatedMonster", "UpdatedType");

        given(monsterRepository.findById(1L)).willReturn(Optional.of(existingMonster));
        given(monsterRepository.save(existingMonster)).willReturn(updatedMonster);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/monsters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"UpdatedMonster\",\"type\":\"UpdatedType\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UpdatedMonster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("UpdatedType"));
    }

    @Test
    void testUpdateMonsterNotFound() throws Exception {
        Monster updatedMonster = new Monster(1L, "UpdatedMonster", "UpdatedType");

        given(monsterRepository.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/monsters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"UpdatedMonster\",\"type\":\"UpdatedType\"}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // Expect 404 NOT FOUND
    }

    @Test
    void testDeleteMonster() throws Exception {
        willDoNothing().given(monsterRepository).deleteById(1L);
        given(monsterRepository.existsById(1L)).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/monsters/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent()); // Expect 204 NO CONTENT
    }

    @Test
    void testDeleteMonsterNotFound() throws Exception {
        given(monsterRepository.existsById(99L)).willReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/monsters/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()); // Expect 404 NOT FOUND
    }
}
