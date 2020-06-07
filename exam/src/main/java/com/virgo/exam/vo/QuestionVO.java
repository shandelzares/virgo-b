package com.virgo.exam.vo;

import com.virgo.exam.model.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionVO {
    private String id;

    private String code;
    /**
     * 分类
     */
    private String category;
    /**
     * 题目类型
     */
    private Question.Type type;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 难度
     */
    private Integer difficult; //难度 1-5
    /**
     * 标题
     */
    private String title;
    /**
     * 题干
     */
    private String stem;
    /**
     * 答案
     */
    private List<Question.Answer> answer;
    /**
     * 简答题打分标准
     */
    private List<Question.ShortAnswerAnalysis> shortAnswerAnalysis;

    /**
     * 解析
     */
    private String analysis;
    private String creator;//创建人code
    private String revisor;//更新人code
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
