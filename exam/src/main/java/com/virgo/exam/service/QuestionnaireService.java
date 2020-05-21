package com.virgo.exam.service;

import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.QuestionnaireQueryParam;
import com.virgo.exam.dto.QuestionnaireSaveParam;
import com.virgo.exam.dto.QuestionnaireSendParam;
import com.virgo.exam.model.ExamPaper;
import com.virgo.exam.model.Questionnaire;
import com.virgo.exam.repository.QuestionnaireRepository;
import com.virgo.exam.vo.ExamPaperVO;
import com.virgo.exam.vo.QuestionnaireVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireService {
    @Resource
    private QuestionnaireRepository questionnaireRepository;


    public PageResult<QuestionnaireVO> findPage(@Valid QuestionnaireQueryParam questionQueryParam) {
        Page<QuestionnaireVO> memberVOS = questionnaireRepository.findAll((Specification<Questionnaire>) (root, query, criteriaBuilder) -> {
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
            predicates.add(criteriaBuilder.equal(root.get("companyCode"), RequestHolder.getCompanyCode()));
            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, questionQueryParam.pageable()).map(article -> {
            QuestionnaireVO vo = new QuestionnaireVO();
            BeanUtils.copyProperties(article, vo);
            return vo;
        });
        return PageResult.of(memberVOS);
    }

    public void save(QuestionnaireSaveParam questionSaveParam) {

    }

    public void send(QuestionnaireSendParam questionnaireSendParam) {

    }
}
