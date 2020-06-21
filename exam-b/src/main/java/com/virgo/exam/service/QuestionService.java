package com.virgo.exam.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.virgo.common.RequestHolder;
import com.virgo.common.page.PageResult;
import com.virgo.exam.dto.QuestionQueryParam;
import com.virgo.exam.dto.QuestionSaveParam;
import com.virgo.exam.model.Question;
import com.virgo.exam.repository.QuestionRepository;
import com.virgo.exam.vo.QuestionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {
    @Resource
    private QuestionRepository questionRepository;

    public PageResult<QuestionVO> findPage(QuestionQueryParam questionQueryParam) {
        Question query = BeanUtil.copyProperties(questionQueryParam, Question.class);
        query.setCompanyCode(RequestHolder.getCompanyCode());

        Page<QuestionVO> memberVOS = questionRepository.findAll(Example.of(query), questionQueryParam.pageable()).map(article -> {
            QuestionVO vo = new QuestionVO();
            BeanUtils.copyProperties(article, vo);
            return vo;
        });
        return PageResult.of(memberVOS);
    }


    public void save(QuestionSaveParam questionSaveParam) {
        Question question;
        if (questionSaveParam.getId() != null) {
            question = questionRepository.findById(questionSaveParam.getId()).orElse(createQuestion());
        } else question = createQuestion();

        BeanUtil.copyProperties(questionSaveParam, question, CopyOptions.create().ignoreNullValue().setIgnoreProperties("correctAnswer"));
        questionRepository.save(question);
    }

    public void remove(String id) {
        questionRepository.deleteById(id);
    }


    private Question createQuestion() {
        Question question = new Question();
        question.setCompanyCode(RequestHolder.getCompanyCode());
        return question;
    }
}
