package com.thetestroom.monster_api.services;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.repositories.MonsterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class MonsterServiceTest {

    @InjectMocks
    private MonsterService monsterService;

    @Mock
    private MonsterRepository monsterRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMonsterById() {
        Monster monster = new Monster(1L, "Dracula", "Vampire");
        when(monsterRepository.findById(1L)).thenReturn(Optional.of(monster));

        Optional<Monster> foundMonster = monsterService.getMonsterById(1L);
        assertThat(foundMonster).isPresent();
        assertThat(foundMonster.get().getName()).isEqualTo("Dracula");
    }
}
