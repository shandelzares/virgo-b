package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.virgo.common.JsonUtils;
import com.virgo.common.RequestHolder;
import com.virgo.common.exception.BusinessException;
import com.virgo.common.exception.ResultEnum;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.ExamPaperQueryParam;
import com.virgo.exam.dto.ExamPaperSaveParam;
import com.virgo.exam.dto.ExamPaperSendParam;
import com.virgo.exam.model.ExamPaper;
import com.virgo.exam.model.ExamPaperQuestion;
import com.virgo.exam.model.PersonalExamPaper;
import com.virgo.exam.repository.ExamPaperQuestionRepository;
import com.virgo.exam.repository.ExamPaperRepository;
import com.virgo.exam.repository.PersonalExamPaperRecordRepository;
import com.virgo.exam.vo.ExamPaperVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperService {
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private ExamPaperQuestionRepository examPaperQuestionRepository;
    @Resource
    private PersonalExamPaperRecordRepository personalExamPaperRecordRepository;

    public PageResult<ExamPaperVO> findPage(@Valid ExamPaperQueryParam questionQueryParam) {
        Page<ExamPaperVO> memberVOS = examPaperRepository.findAll((Specification<ExamPaper>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(questionQueryParam.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), questionQueryParam.getCode() + "%"));
            }

            if (!StringUtils.isEmpty(questionQueryParam.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), questionQueryParam.getTitle() + "%"));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getCategory())) {
                predicates.add(criteriaBuilder.equal(root.get("category"), questionQueryParam.getCategory()));
            }
            if (!CollectionUtils.isEmpty(questionQueryParam.getType())) {
                CriteriaBuilder.In<ExamPaper.Type> in = criteriaBuilder.in(root.get("type"));
                questionQueryParam.getType().forEach(in::value);
                predicates.add(criteriaBuilder.and(in));
            }
            predicates.add(criteriaBuilder.equal(root.get("companyCode"), RequestHolder.getCompanyCode()));
            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, questionQueryParam.pageable()).map(article -> {
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
            examPaper = examPaperRepository.findById(examPaperSaveParam.getId()).map(ep -> {
                examPaperQuestionRepository.deleteAllByExamPaper(ep); //删除历史题目
                return ep;
            }).orElse(createMenu());
        } else examPaper = createMenu();
        BeanUtil.copyProperties(examPaperSaveParam, examPaper, CopyOptions.create().ignoreNullValue());
        examPaperRepository.save(examPaper);
        List<ExamPaperQuestion> examPaperQuestions = examPaperSaveParam.getQuestions().stream().map(question -> {
            ExamPaperQuestion examPaperQuestion = new ExamPaperQuestion();
            BeanUtil.copyProperties(question, examPaperQuestion, CopyOptions.create().ignoreNullValue());
            examPaperQuestion.setExamPaper(examPaper);
            examPaperQuestion.setQuestionId(question.getId()); //对应题库id
            examPaperQuestion.setId(null);//设置自增id
            examPaperQuestion.setAnswer(JsonUtils.toJson(question.getAnswer()));
            examPaperQuestion.setCorrectAnswer(JsonUtils.toJson(question.getCorrectAnswer()));
            return examPaperQuestion;
        }).collect(Collectors.toList());
        examPaperQuestionRepository.saveAll(examPaperQuestions);
    }

    public void remove(Long id) {
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
                List<PersonalExamPaper> records = questionSaveParam.getUserIds().stream().map(it -> {
                    PersonalExamPaper record = new PersonalExamPaper();
                    record.setExamPaperId(examPaper.getId());
                    record.setMemberId(it);
                    return record;
                }).collect(Collectors.toList());
                personalExamPaperRecordRepository.saveAll(records);
            }
            examPaperRepository.save(examPaper);
        }, () -> {
            throw new BusinessException(ResultEnum.PARAM_ERROR);//todo
        });
    }
}
