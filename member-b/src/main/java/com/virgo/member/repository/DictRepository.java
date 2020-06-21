package com.virgo.member.repository;

import com.virgo.member.model.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DictRepository extends JpaRepository<Dict, Long> , JpaSpecificationExecutor<Dict> {

    Optional<Dict> findByCode(String code);
    Optional<Dict> findByCodeAndCompanyCode(String code,String companyCode);
}
