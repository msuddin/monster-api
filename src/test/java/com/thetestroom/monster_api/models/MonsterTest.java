package com.thetestroom.monster_api.models;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MonsterTest {

    @Test
    public void testMonsterCreation() {
        Monster monster = new Monster(1L, "Dracula", "Vampire");

        assertThat(monster.getId()).isEqualTo(1L);
        assertThat(monster.getName()).isEqualTo("Dracula");
        assertThat(monster.getType()).isEqualTo("Vampire");
    }
}
