package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.virgo.common.JsonUtils;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.QuestionQueryParam;
import com.virgo.exam.dto.QuestionSaveParam;
import com.virgo.exam.model.Question;
import com.virgo.exam.repository.QuestionRepository;
import com.virgo.exam.vo.QuestionVO;
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

@Service
public class QuestionService {
    @Resource
    private QuestionRepository questionRepository;

    public PageResult<QuestionVO> findPage(QuestionQueryParam questionQueryParam) {
        Page<QuestionVO> memberVOS = questionRepository.findAll((Specification<Question>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(questionQueryParam.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), questionQueryParam.getCode() + "%"));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getLevel())) {
                predicates.add(criteriaBuilder.equal(root.get("level"), questionQueryParam.getLevel()));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getScore())) {
                predicates.add(criteriaBuilder.equal(root.get("score"), questionQueryParam.getScore()));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), questionQueryParam.getTitle() + "%"));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getTags())) {
                predicates.add(criteriaBuilder.equal(root.get("tags"), questionQueryParam.getTags()));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getCompanyCode())) {
                predicates.add(criteriaBuilder.equal(root.get("companyCode"), questionQueryParam.getCompanyCode()));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getDifficult())) {
                predicates.add(criteriaBuilder.equal(root.get("difficult"), questionQueryParam.getDifficult()));
            }
            if (!StringUtils.isEmpty(questionQueryParam.getCategory())) {
                predicates.add(criteriaBuilder.equal(root.get("category"), questionQueryParam.getCategory()));
            }
            if (!CollectionUtils.isEmpty(questionQueryParam.getType())) {
                CriteriaBuilder.In<Question.Type> in = criteriaBuilder.in(root.get("type"));
                questionQueryParam.getType().forEach(in::value);
                predicates.add(criteriaBuilder.and(in));
            }
            predicates.add(criteriaBuilder.equal(root.get("companyCode"), RequestHolder.getCompanyCode()));
            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, questionQueryParam.pageable()).map(article -> {
            QuestionVO vo = new QuestionVO();
            BeanUtils.copyProperties(article, vo, "answer", "correctAnswer");
            vo.setAnswer(JsonUtils.parse(article.getAnswer(), new TypeReference<>() {
            }));
            vo.setCorrectAnswer(JsonUtils.parse(article.getCorrectAnswer(), new TypeReference<>() {
            }));
            return vo;
        });
        return PageResult.of(memberVOS);
    }

    public void save(QuestionSaveParam questionSaveParam) {
        Question menu;
        if (questionSaveParam.getId() != null) {
            menu = questionRepository.findById(questionSaveParam.getId()).orElse(createMenu());
        } else menu = createMenu();

        BeanUtil.copyProperties(questionSaveParam, menu, CopyOptions.create().ignoreNullValue().setIgnoreProperties("answer", "correctAnswer"));

        menu.setAnswer(JsonUtils.toJson(questionSaveParam.getAnswer()));
        menu.setCorrectAnswer(JsonUtils.toJson(questionSaveParam.getCorrectAnswer()));
        questionRepository.save(menu);
    }

    public void remove(Long id) {
        questionRepository.deleteById(id);
    }


    private Question createMenu() {
        Question question = new Question();
        question.setCompanyCode(RequestHolder.getCompanyCode());
        return question;
    }
}
