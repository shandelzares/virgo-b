package com.virgo.member.repository;

import com.virgo.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
    Optional<Member> findByPhoneAndDeletedIsFalse(String phone);

    Optional<Member> findByMemberId(String memberId);

}
