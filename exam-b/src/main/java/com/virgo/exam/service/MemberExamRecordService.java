package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.memberExamRecordServiceQueryParam;
import com.virgo.exam.model.PublishExamPaper;
import com.virgo.exam.repository.PublishExamPaperRepository;
import com.virgo.exam.vo.PublishExamPaperVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberExamRecordService {
    @Resource
    private PublishExamPaperRepository publishExamPaperRepository;


    public PageResult<PublishExamPaperVO> findPage(memberExamRecordServiceQueryParam questionQueryParam) {
        PublishExamPaper query = BeanUtil.copyProperties(questionQueryParam, PublishExamPaper.class);
        query.setCompanyCode(RequestHolder.getCompanyCode());
        Page<PublishExamPaperVO> memberVOS = publishExamPaperRepository.findAll(Example.of(query), questionQueryParam.pageable())
                .map(article -> {
                    PublishExamPaperVO vo = new PublishExamPaperVO();
                    BeanUtils.copyProperties(article, vo);
                    return vo;
                });
        return PageResult.of(memberVOS);
    }
}
