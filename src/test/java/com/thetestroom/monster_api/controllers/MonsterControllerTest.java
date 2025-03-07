package com.thetestroom.monster_api.controllers;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.services.MonsterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MonsterController.class)
public class MonsterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonsterService monsterService;

    private Monster monster1;
    private Monster monster2;

    @BeforeEach
    void setUp() {
        monster1 = new Monster(1L, "Dracula", "Vampire");
        monster2 = new Monster(2L, "Frankenstein", "Undead");
    }

    @Test
    void testGetAllMonsters() throws Exception {
        when(monsterService.getAllMonsters()).thenReturn(Arrays.asList(monster1, monster2));

        mockMvc.perform(get("/api/monsters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Dracula"))
                .andExpect(jsonPath("$[1].name").value("Frankenstein"));
    }

    @Test
    void testGetMonsterById_Found() throws Exception {
        when(monsterService.getMonsterById(1L)).thenReturn(Optional.of(monster1));

        mockMvc.perform(get("/api/monsters/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dracula"))
                .andExpect(jsonPath("$.type").value("Vampire"));
    }

    @Test
    void testGetMonsterById_NotFound() throws Exception {
        when(monsterService.getMonsterById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/monsters/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateMonster() throws Exception {
        Monster newMonster = new Monster(3L, "Werewolf", "Beast");
        when(monsterService.createMonster(Mockito.any(Monster.class))).thenReturn(newMonster);

        String monsterJson = """
            {
                "id": 3,
                "name": "Werewolf",
                "type": "Beast"
            }
        """;

        mockMvc.perform(post("/api/monsters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(monsterJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Werewolf"))
                .andExpect(jsonPath("$.type").value("Beast"));
    }

    @Test
    void testUpdateMonster_Success() throws Exception {
        when(monsterService.updateMonster(Mockito.eq(1L), Mockito.any(Monster.class)))
                .thenReturn(Optional.of(new Monster(1L, "Updated Dracula", "Vampire")));

        String updatedJson = """
            {
                "name": "Updated Dracula",
                "type": "Vampire"
            }
        """;

        mockMvc.perform(put("/api/monsters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Dracula"));
    }

    @Test
    void testUpdateMonster_NotFound() throws Exception {
        when(monsterService.updateMonster(Mockito.eq(999L), Mockito.any(Monster.class)))
                .thenReturn(Optional.empty());

        String updatedJson = """
            {
                "name": "Updated Name",
                "type": "Updated Type"
            }
        """;

        mockMvc.perform(put("/api/monsters/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteMonster_Success() throws Exception {
        when(monsterService.deleteMonster(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/monsters/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteMonster_NotFound() throws Exception {
        when(monsterService.deleteMonster(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/monsters/999"))
                .andExpect(status().isNotFound());
    }
}
