package com.virgo.member.repository;

import com.virgo.member.model.CompanyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMenuRepository extends JpaRepository<CompanyMenu, Long> {
    List<CompanyMenu> findByCompanyCode(String companyCode);
}
