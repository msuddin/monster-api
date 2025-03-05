package com.thetestroom.monster_api.controllers;

import com.thetestroom.monster_api.models.Monster;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/monsters")
public class MonsterController {

    private final List<Monster> monsters = new ArrayList<>(List.of(
            new Monster("1", "Godzilla", "Kaiju"),
            new Monster("2", "Dracula", "Vampire"),
            new Monster("3", "Bigfoot", "Cryptid")
    ));

    @GetMapping
    public List<Monster> getAllMonsters() {
        return monsters;
    }

    @PostMapping
    public Monster createMonster(@RequestBody Monster monster) {
        monster.setId(UUID.randomUUID().toString());
        monsters.add(monster);
        return monster;
    }
}
