package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.virgo.common.JsonUtils;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.ExamPaperQueryParam;
import com.virgo.exam.dto.ExamPaperSaveParam;
import com.virgo.exam.dto.QuestionQueryParam;
import com.virgo.exam.dto.QuestionSaveParam;
import com.virgo.exam.model.ExamPaper;
import com.virgo.exam.model.Question;
import com.virgo.exam.repository.ExamPaperRepository;
import com.virgo.exam.vo.ExamPaperVO;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExamPaperService {
    @Resource
    private ExamPaperRepository examPaperRepository;

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
            query.where(predicates.toArray(new Predicate[0])).orderBy();
            return query.getRestriction();
        }, questionQueryParam.pageable()).map(article -> {
            ExamPaperVO vo = new ExamPaperVO();
            BeanUtils.copyProperties(article, vo);
//            vo.setAnswer(JsonUtils.parse(article.getAnswer(), new TypeReference<>() {
//            }));
//            vo.setCorrectAnswer(JsonUtils.parse(article.getCorrectAnswer(), new TypeReference<>() {
//            }));
            return vo;
        });
        return PageResult.of(memberVOS);
    }

    public void save(ExamPaperSaveParam questionSaveParam) {
        ExamPaper menu;
        if (questionSaveParam.getId() != null) {
            menu = examPaperRepository.findById(questionSaveParam.getId()).orElse(createMenu());
        } else menu = createMenu();

        BeanUtil.copyProperties(questionSaveParam, menu, CopyOptions.create().ignoreNullValue().setIgnoreProperties("answer", "correctAnswer"));

//        menu.setAnswer(JsonUtils.toJson(questionSaveParam.getAnswer()));
//        menu.setCorrectAnswer(JsonUtils.toJson(questionSaveParam.getCorrectAnswer()));
        examPaperRepository.save(menu);
    }

    public void remove(Long id) {
        examPaperRepository.deleteById(id);
    }


    private ExamPaper createMenu() {
        ExamPaper question = new ExamPaper();
        question.setCompanyCode(RequestHolder.getCompanyCode());
        return question;
    }
}
