package com.virgo.member.repository;

import com.virgo.member.model.Category;
import com.virgo.member.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCompanyCode(String companyCode);
}
