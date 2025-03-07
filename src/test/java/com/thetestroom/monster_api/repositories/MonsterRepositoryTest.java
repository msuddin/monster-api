package com.thetestroom.monster_api.repositories;

import com.thetestroom.monster_api.models.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
public class MonsterRepositoryTest {

    @Autowired
    private MonsterRepository monsterRepository;

    @Test
    public void testSaveAndFindById() {
        Monster monster = new Monster(1L, "Dracula", "Vampire");
        monsterRepository.save(monster);

        Optional<Monster> foundMonster = monsterRepository.findById(1L);
        assertThat(foundMonster).isPresent();
        assertThat(foundMonster.get().getName()).isEqualTo("Dracula");
        assertThat(foundMonster.get().getType()).isEqualTo("Vampire");
    }
}
