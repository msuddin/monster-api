package com.thetestroom.monster_api.services;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.repositories.MonsterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonsterServiceTest {

    @Mock
    private MonsterRepository monsterRepository;

    @InjectMocks
    private MonsterService monsterService;

    @Test
    void testGetAllMonsters() {
        // Given
        Monster monster1 = new Monster(1L, "Monster1", "Type1");
        Monster monster2 = new Monster(2L, "Monster2", "Type2");
        when(monsterRepository.findAll()).thenReturn(List.of(monster1, monster2));

        // When
        var monsters = monsterService.getAllMonsters();

        // Then
        assertEquals(2, monsters.size());
        assertEquals("Monster1", monsters.get(0).getName());
        assertEquals("Monster2", monsters.get(1).getName());
        verify(monsterRepository, times(1)).findAll();
    }

    @Test
    void testGetMonsterById() {
        // Given
        Monster monster = new Monster(1L, "Monster1", "Type1");
        when(monsterRepository.findById(1L)).thenReturn(Optional.of(monster));

        // When
        var foundMonster = monsterService.getMonsterById(1L);

        // Then
        assertTrue(foundMonster.isPresent());
        assertEquals("Monster1", foundMonster.get().getName());
        verify(monsterRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateMonster() {
        // Given
        Monster monster = new Monster(1L, "Monster1", "Type1");
        when(monsterRepository.save(monster)).thenReturn(monster);

        // When
        var createdMonster = monsterService.createMonster(monster);

        // Then
        assertNotNull(createdMonster);
        assertEquals("Monster1", createdMonster.getName());
        verify(monsterRepository, times(1)).save(monster);
    }

    @Test
    void testUpdateMonster() {
        // Given
        Monster existingMonster = new Monster(1L, "Monster1", "Type1");
        Monster updatedMonster = new Monster(1L, "UpdatedMonster", "UpdatedType");
        when(monsterRepository.findById(1L)).thenReturn(Optional.of(existingMonster));
        when(monsterRepository.save(existingMonster)).thenReturn(updatedMonster);

        // When
        var result = monsterService.updateMonster(1L, updatedMonster);

        // Then
        assertTrue(result.isPresent());
        assertEquals("UpdatedMonster", result.get().getName());
        assertEquals("UpdatedType", result.get().getType());
        verify(monsterRepository, times(1)).findById(1L);
        verify(monsterRepository, times(1)).save(existingMonster);
    }

    @Test
    void testDeleteMonster() {
        // Given
        when(monsterRepository.existsById(1L)).thenReturn(true);

        // When
        boolean result = monsterService.deleteMonster(1L);

        // Then
        assertTrue(result);
        verify(monsterRepository, times(1)).existsById(1L);
        verify(monsterRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteMonsterNotFound() {
        // Given
        when(monsterRepository.existsById(1L)).thenReturn(false);

        // When
        boolean result = monsterService.deleteMonster(1L);

        // Then
        assertFalse(result);
        verify(monsterRepository, times(1)).existsById(1L);
    }
}
