package com.virgo.member.repository;

import com.virgo.member.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
