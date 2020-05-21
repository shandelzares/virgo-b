package com.virgo.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.member.dto.DictQueryParam;
import com.virgo.member.dto.DictSaveParam;
import com.virgo.member.model.Dict;
import com.virgo.member.repository.DictRepository;
import com.virgo.member.vo.DictVO;
import com.virgo.member.vo.MenuVO;
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
public class DictService {
    @Resource
    private DictRepository dictRepository;

    public PageResult<DictVO> findDictPage(DictQueryParam dictQueryParam) {
        Page<Dict> dictVOS = dictRepository.findAll((Specification<Dict>) (root, query, criteriaBuilder) -> {
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
        }, dictQueryParam.pageable());
        List<DictVO> all = dictVOS.getContent().stream()
                .map(article -> {
                    DictVO vo = new DictVO();
                    BeanUtils.copyProperties(article, vo);
                    return vo;
                }).collect(Collectors.toList());
        List<DictVO> parent = all.stream().filter(it -> Objects.equals(it.getIsTop(), true)).collect(Collectors.toList());//总公司
        List<DictVO> pk = parent.stream().peek(it -> {
            List<DictVO> exist = all.stream().filter(f -> Objects.equals(it.getCode(), f.getCode()) && !Objects.equals(f.getIsTop(), true)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(exist)) {
                it.setChildren(exist);
            }
        }).collect(Collectors.toList());


        return new PageResult<>(dictVOS.getTotalElements(), pk);
    }

    public void save(DictSaveParam dictSaveParam) {
        //如果是1000000 公司代码，则根据传入companyCode查找，否则只能查找自己的数据
        String companyCode = (Objects.equals(RequestHolder.getCompanyCode(), "1000000")) ? dictSaveParam.getCompanyCode() : RequestHolder.getCompanyCode();
        dictRepository.findByCodeAndCompanyCode(dictSaveParam.getCode(), companyCode).ifPresentOrElse(dict -> {
            BeanUtil.copyProperties(dictSaveParam, dict, CopyOptions.create().setIgnoreProperties("id", "code", "creator", "revisor", "updateTime", "createTime", "version", "companyCode"));
            dictRepository.save(dict);
        }, () -> {
            Dict dict = new Dict();
            BeanUtil.copyProperties(dictSaveParam, dict, CopyOptions.create().setIgnoreProperties("id", "creator", "revisor", "updateTime", "createTime", "version"));
            if (dict.getCompanyCode() == null)
                dict.setCompanyCode(companyCode);
            dictRepository.save(dict);
        });
    }

    public DictVO findByCode(String code) {
        return dictRepository.findByCodeAndCompanyCode(code, RequestHolder.getCompanyCode()).map(it -> {
            return BeanUtil.copyProperties(it, DictVO.class);
        }).orElse(null);
    }
}
