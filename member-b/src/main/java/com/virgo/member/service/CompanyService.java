package com.virgo.member.service;

import cn.hutool.core.bean.BeanUtil;
import com.virgo.member.repository.CompanyRepository;
import com.virgo.member.vo.CompanyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Resource
    private CompanyRepository companyRepository;

    public List<CompanyVO> findAll() {
        return companyRepository.findAll().stream().map(it -> {
            return BeanUtil.copyProperties(it, CompanyVO.class);
        }).collect(Collectors.toList());
    }
}
