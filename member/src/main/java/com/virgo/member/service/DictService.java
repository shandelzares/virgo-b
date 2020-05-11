package com.virgo.member.service;

import com.virgo.common.page.PageResult;
import com.virgo.member.dto.DictQueryParam;
import com.virgo.member.dto.DictSaveParam;
import com.virgo.member.model.Dict;
import com.virgo.member.repository.DictRepository;
import com.virgo.member.vo.DictVO;
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
public class DictService {
    @Resource
    private DictRepository dictRepository;

    public PageResult<DictVO> findDictPage(DictQueryParam dictQueryParam) {
        Page<DictVO> dictVOS = dictRepository.findAll((Specification<Dict>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(dictQueryParam.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), dictQueryParam.getCode() + "%"));
            }
            if (!StringUtils.isEmpty(dictQueryParam.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), dictQueryParam.getName() + "%"));
            }
            if (!CollectionUtils.isEmpty(dictQueryParam.getType())) {
                CriteriaBuilder.In<Dict.Type> in = criteriaBuilder.in(root.get("type"));
                dictQueryParam.getType().forEach(in::value);
                predicates.add(criteriaBuilder.and(in));
            }
            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, dictQueryParam.pageable()).map(article -> {
            DictVO vo = new DictVO();
            BeanUtils.copyProperties(article, vo);
            return vo;
        });
        return PageResult.of(dictVOS);
    }

    public void save(DictSaveParam dictSaveParam) {

    }
}
