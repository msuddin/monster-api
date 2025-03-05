package com.thetestroom.monster_api.repositories;

import com.thetestroom.monster_api.models.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    // Custom queries can go here if needed
}
