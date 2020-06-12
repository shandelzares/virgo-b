package com.virgo.exam.dto;

import com.virgo.exam.model.ExamPaper;
import com.virgo.exam.model.Question;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamPaperSaveParam {
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 分类
     */
    private String category;
    /**
     * 试卷类型
     */
    private ExamPaper.Type type;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 及格线
     */
    private Integer passScore;      //及格线
    /**
     * 平均难度
     */
    private Float avgDifficult;   //平均难度
    /**
     * 考试时长
     */
    private Integer examTime;       //考试时间 秒
    /**
     * 考试开始时间
     */
    private LocalDateTime examStartTime; //考试开始时间
    /**
     * 考试结束时间
     */
    private LocalDateTime examEndTime; //考试结束时间
    /**
     * 允许切屏次数
     */
    private Integer switchScreen;       //切屏次数
    /**
     * 是否全屏作答
     */
    private Boolean fullScreen;         //是否全屏作答
    /**
     * 考试难度 1-5
     */
    private Integer difficult;      //设定难度
    /**
     * 最多考试次数
     */
    private Integer maxExamCount;   //最多考试次数
    /**
     * 是否自动阅卷
     */
    private Boolean autoScoring;    //自动阅卷
    /**
     * 题目个数
     */
    private Integer questionCount;   //最多考试次数
    /**
     * 题目乱序
     */
    private Boolean questionDerangement;    //题目乱序
    /**
     * 选项乱序
     */
    private Boolean optionsDerangement;     //选项乱序

    @Transient
    private List<ExamPaperQuestionParam> questions;


    @Data
    public static class ExamPaperQuestionParam {
        private String id;
        /**
         * 题目id
         */
        private String questionId;
        /**
         * 试卷id
         */
        private String examPaperId;
        /**
         * 编码
         */
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

        private Question.RandomConfig randomConfig;
    }
}
