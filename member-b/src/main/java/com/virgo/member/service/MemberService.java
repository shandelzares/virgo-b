package com.virgo.member.service;

import cn.hutool.core.bean.BeanUtil;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.member.dto.MemberQueryByUserIdsParam;
import com.virgo.member.dto.MemberQueryParam;
import com.virgo.member.model.Member;
import com.virgo.member.model.Organization;
import com.virgo.member.repository.MemberRepository;
import com.virgo.member.repository.OrganizationRepository;
import com.virgo.member.vo.MemberVO;
import com.virgo.member.vo.OrganizationVO;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {
    @Resource
    private MemberRepository memberRepository;
    @Resource
    private OrganizationRepository organizationRepository;

    public MemberVO findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).map(member -> {
            MemberVO vo = new MemberVO();
            BeanUtils.copyProperties(member, vo);
            return vo;
        }).orElse(null);
    }

    public PageResult<MemberVO> findPage(MemberQueryParam memberQueryParam) {
        Page<Member> memberVOS = memberRepository.findAll((Specification<Member>) (root, query, criteriaBuilder) -> {
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
            if (!StringUtils.isEmpty(memberQueryParam.getOrganizationId())) {
                predicates.add(criteriaBuilder.equal(root.get("organizationId"), memberQueryParam.getOrganizationId()));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), memberQueryParam.getName() + "%"));
            }
            if (!StringUtils.isEmpty(memberQueryParam.getSex())) {
                predicates.add(criteriaBuilder.equal(root.get("sex"), memberQueryParam.getSex()));
            }

            if (Objects.equals(RequestHolder.getCompanyCode(), "1000000")) {
                if (!StringUtils.isEmpty(memberQueryParam.getCompanyCode())) {
                    predicates.add(criteriaBuilder.equal(root.get("companyCode"), memberQueryParam.getCompanyCode()));
                }
            } else {
                predicates.add(criteriaBuilder.equal(root.get("companyCode"), RequestHolder.getCompanyCode()));
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
        }, memberQueryParam.pageable());

        List<Organization> organizations = organizationRepository.findByIdIn(memberVOS.getContent().stream().filter(member -> member.getOrganizationId() != null).map(Member::getOrganizationId).collect(Collectors.toList()));


        //map(article -> {
        //            MemberVO vo = new MemberVO();
        //            BeanUtils.copyProperties(article, vo);
        //            return vo;
        //        });
        return new PageResult<>(memberVOS.getTotalElements(), memberVOS.getContent().stream().map(member -> {
            MemberVO memberVO = BeanUtil.copyProperties(member, MemberVO.class);
            organizations.stream().filter(organization -> Objects.equals(member.getOrganizationId(), organization.getId())).findFirst().ifPresent(organization -> {
                memberVO.setOrganization(BeanUtil.copyProperties(organization, OrganizationVO.class));
            });
            return memberVO;
        }).collect(Collectors.toList()));
    }

    public List<MemberVO> findByIds(MemberQueryByUserIdsParam param) {
        List<Member> members = memberRepository.findByIdIn(param.getUserIds());
        if (CollectionUtils.isEmpty(members))
            return null;

        List<Organization> organizations = organizationRepository.findByIdIn(members.stream().filter(member -> member.getOrganizationId() != null).map(Member::getOrganizationId).collect(Collectors.toList()));

        return members.stream().map(member -> {
            MemberVO memberVO = BeanUtil.copyProperties(member, MemberVO.class);
            organizations.stream().filter(organization -> Objects.equals(member.getOrganizationId(), organization.getId())).findFirst().ifPresent(organization -> {
                memberVO.setOrganization(BeanUtil.copyProperties(organization, OrganizationVO.class));
            });
            return memberVO;
        }).collect(Collectors.toList());
    }
}
