package com.virgo.exam.dto;

import com.virgo.exam.model.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamPaperSaveParam {
    private Long id;
    private String code;
    private String name;
    private String difficult;
    private String category;
    private Integer lastTestScore;  //上次考试分数
    private Integer passScore;      //及格线
    private Integer avgDifficult;   //平均难度
    private Integer examTime;       //考试时间 秒
    private LocalDateTime examStartTime; //考试开始时间
    private LocalDateTime examEndTime; //考试结束时间
    private Integer switchScreen;       //切屏次数
    private Boolean fullScreen;         //是否全屏作答
    private Integer maxExamCount;   //最多考试次数
    private Integer questionCount;   //最多考试次数
    private Integer questionScoreTotal;
    private List<ExamPaperQuestionParam> questions;


    @Data
    public static class ExamPaperQuestionParam {
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
         * {
         * "prefix": "A",
         * "content": "A选项",
         * "score": 1
         * }
         */
        private List<QuestionSaveParam.Answer> answer;
        private List<String> correctAnswer;
        /**
         *
         */
        private String tags;
        /**
         * 解析
         */
        private String analysis;

    }
}
