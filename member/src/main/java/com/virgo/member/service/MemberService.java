package com.virgo.member.service;

import com.virgo.common.page.PageResult;
import com.virgo.member.dto.MemberQueryParam;
import com.virgo.member.model.Member;
import com.virgo.member.repository.MemberRepository;
import com.virgo.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberService {
    @Resource
    private MemberRepository memberRepository;

    public MemberVO findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).map(member -> {
            MemberVO vo = new MemberVO();
            BeanUtils.copyProperties(member, vo);
            return vo;
        }).orElse(null);
    }

    public PageResult<MemberVO> findPage(MemberQueryParam memberQueryParam) {
        Page<MemberVO> memberVOS = memberRepository.findAll((Specification<Member>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(memberQueryParam.getMemberId())) {
                predicates.add(criteriaBuilder.like(root.get("memberId"), memberQueryParam.getMemberId() + "%"));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getPhone())) {
                predicates.add(criteriaBuilder.like(root.get("phone"), memberQueryParam.getPhone() + "%"));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getNickName())) {
                predicates.add(criteriaBuilder.like(root.get("nickName"), memberQueryParam.getNickName() + "%"));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), memberQueryParam.getName() + "%"));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getSex())) {
                predicates.add(criteriaBuilder.equal(root.get("sex"), memberQueryParam.getSex()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getCompanyCode())) {
                predicates.add(criteriaBuilder.equal(root.get("companyCode"), memberQueryParam.getCompanyCode()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getDeleted())) {
                predicates.add(criteriaBuilder.equal(root.get("deleted"), memberQueryParam.getDeleted()));
            }

            if (!CollectionUtils.isEmpty(memberQueryParam.getType())) {
                CriteriaBuilder.In<Member.Type> in = criteriaBuilder.in(root.get("type"));
                memberQueryParam.getType().forEach(in::value);
                predicates.add(criteriaBuilder.and(in));
            }
            if (!CollectionUtils.isEmpty(memberQueryParam.getStatus())) {
                CriteriaBuilder.In<Member.Status> in = criteriaBuilder.in(root.get("status"));
                memberQueryParam.getStatus().forEach(in::value);
                predicates.add(criteriaBuilder.and(in));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getBirthdayStart())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), memberQueryParam.getBirthdayStart()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getBirthdayEnd())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), memberQueryParam.getBirthdayEnd()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getCreateTimeStart())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), memberQueryParam.getCreateTimeStart()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getCreateTimeEnd())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), memberQueryParam.getCreateTimeEnd()));
            }

            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, memberQueryParam.pageable()).map(article -> {
            MemberVO vo = new MemberVO();
            BeanUtils.copyProperties(article, vo);
            return vo;
        });
        return PageResult.of(memberVOS);
    }
}
