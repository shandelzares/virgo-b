package com.virgo.exam.dot;

import com.virgo.exam.model.Question;
import lombok.Data;

@Data
public class QuestionSaveParam {
    private Long id;
    private String code;
    /**
     * 分类
     */
    private String category;
    private Question.Type type;
    /**
     * 级别（等级、年级等）
     */
    private String level;
    private Integer score;
    private Integer difficult; //难度 1-10
    private String title;
    private String content;
    /**
     * json格式
     *{
     * "prefix": "A",
     * "content": "A选项",
     * "score": 1
     * }
     */
    private String answer;
    private String correctAnswer;
    /**
     *
     */
    private String tags;
    /**
     * 解析
     */
    private String analysis;
    private Long version;
    private String companyCode;

}
