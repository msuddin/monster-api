package com.thetestroom.monster_api.services;

import com.thetestroom.monster_api.models.Monster;
import com.thetestroom.monster_api.repositories.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonsterService {

    private final MonsterRepository monsterRepository;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    // Get all monsters
    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }

    // Get monster by ID
    public Optional<Monster> getMonsterById(Long id) {
        return monsterRepository.findById(id);
    }

    // Create a new monster
    public Monster createMonster(Monster monster) {
        return monsterRepository.save(monster);
    }

    // Update an existing monster
    public Optional<Monster> updateMonster(Long id, Monster monsterDetails) {
        return monsterRepository.findById(id).map(existingMonster -> {
            existingMonster.setName(monsterDetails.getName());
            existingMonster.setType(monsterDetails.getType());
            return monsterRepository.save(existingMonster);
        });
    }

    // Delete a monster
    public boolean deleteMonster(Long id) {
        if (monsterRepository.existsById(id)) {
            monsterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
