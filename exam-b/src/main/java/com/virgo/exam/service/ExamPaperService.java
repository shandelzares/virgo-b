package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.virgo.common.RequestHolder;
import com.virgo.common.exception.BusinessException;
import com.virgo.common.exception.ResultEnum;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.ExamPaperQueryParam;
import com.virgo.exam.dto.ExamPaperSaveParam;
import com.virgo.exam.dto.ExamPaperSendParam;
import com.virgo.exam.model.ExamPaper;
import com.virgo.exam.model.ExamPaperQuestion;
import com.virgo.exam.model.PublishExamPaper;
import com.virgo.exam.repository.ExamPaperQuestionRepository;
import com.virgo.exam.repository.ExamPaperRepository;
import com.virgo.exam.repository.PublishExamPaperRepository;
import com.virgo.exam.vo.ExamPaperVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperService {
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private ExamPaperQuestionRepository examPaperQuestionRepository;

    @Resource
    private PublishExamPaperRepository publishExamPaperRepository;

    public PageResult<ExamPaperVO> findPage(@Valid ExamPaperQueryParam questionQueryParam) {
        ExamPaper query = BeanUtil.copyProperties(questionQueryParam, ExamPaper.class);
        query.setCompanyCode(RequestHolder.getCompanyCode());
        Page<ExamPaperVO> memberVOS = examPaperRepository.findAll(Example.of(query), questionQueryParam.pageable())
                .map(article -> {
                    ExamPaperVO vo = new ExamPaperVO();
                    BeanUtils.copyProperties(article, vo);
                    return vo;
                });
        return PageResult.of(memberVOS);
    }

    @Transactional
    public void save(ExamPaperSaveParam examPaperSaveParam) {
        ExamPaper examPaper;
        if (examPaperSaveParam.getId() != null) {
            examPaper = examPaperRepository.findById(examPaperSaveParam.getId()).orElse(createMenu());
        } else examPaper = createMenu();
        BeanUtil.copyProperties(examPaperSaveParam, examPaper, CopyOptions.create().ignoreNullValue());
        examPaperRepository.save(examPaper);
        List<ExamPaperQuestion> questions = examPaperSaveParam.getQuestions().stream().map(it->{
            ExamPaperQuestion question = BeanUtil.copyProperties(it,ExamPaperQuestion.class);
            question.setExamPaperId(examPaper.getId());
            question.setId(null);
            return question;
        }).collect(Collectors.toList());
        examPaperQuestionRepository.saveAll(questions);
    }

    public void remove(String id) {
        examPaperRepository.findById(id).ifPresentOrElse(examPaper -> {
            examPaper.setStatus(ExamPaper.Status.DELETED);
            examPaperRepository.save(examPaper);
        }, () -> {
            throw new BusinessException(ResultEnum.PARAM_ERROR);//todo
        });
    }


    private ExamPaper createMenu() {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setStatus(ExamPaper.Status.DRAFT);
        examPaper.setCompanyCode(RequestHolder.getCompanyCode());
        return examPaper;
    }

    @Transactional
    public void send(ExamPaperSendParam questionSaveParam) {
        examPaperRepository.findById(questionSaveParam.getExamPaperId()).ifPresentOrElse(examPaper -> {
            examPaper.setStatus(ExamPaper.Status.PUBLISHED);
            if (questionSaveParam.getType() == ExamPaperSendParam.Type.ASSIGN) {//发送至指定的人
                List<PublishExamPaper> records = questionSaveParam.getUserIds().stream().map(it -> {
                    PublishExamPaper record = new PublishExamPaper();
                    record.setExamPaperId(examPaper.getId());
                    record.setUserId(it + "");
                    record.setCompanyCode(RequestHolder.getCompanyCode());
                    record.setExamCount(0);
                    return record;
                }).collect(Collectors.toList());
                publishExamPaperRepository.saveAll(records);
            }
            examPaperRepository.save(examPaper);
        }, () -> {
            throw new BusinessException(ResultEnum.PARAM_ERROR);//todo
        });
    }
}
