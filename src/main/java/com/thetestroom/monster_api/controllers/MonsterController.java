package com.thetestroom.monster_api.controllers;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.repositories.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {

    @Autowired
    private MonsterRepository monsterRepository;

    // Get all monsters
    @GetMapping
    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }

    // Get a single monster by ID
    @GetMapping("/{id}")
    public ResponseEntity<Monster> getMonsterById(@PathVariable Long id) {
        Optional<Monster> monster = monsterRepository.findById(id);
        return monster.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new monster
    @PostMapping
    public ResponseEntity<Monster> createMonster(@RequestBody Monster monster) {
        Monster savedMonster = monsterRepository.save(monster);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMonster);
    }

    // Update an existing monster
    @PutMapping("/{id}")
    public ResponseEntity<Monster> updateMonster(@PathVariable Long id, @RequestBody Monster monsterDetails) {
        Optional<Monster> monsterOptional = monsterRepository.findById(id);
        if (monsterOptional.isPresent()) {
            Monster monster = monsterOptional.get();
            monster.setName(monsterDetails.getName());
            monster.setType(monsterDetails.getType());
            Monster updatedMonster = monsterRepository.save(monster);
            return ResponseEntity.ok(updatedMonster);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a monster
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonster(@PathVariable Long id) {
        if (monsterRepository.existsById(id)) {
            monsterRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
