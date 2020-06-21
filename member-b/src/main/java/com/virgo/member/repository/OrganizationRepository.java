package com.virgo.member.repository;

import com.virgo.member.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {
    List<Organization> findAllByCompanyCode(String companyCode);
    List<Organization> findByIdIn(List<Long> ids);
}
