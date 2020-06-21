package com.virgo.exam.vo;

import com.virgo.exam.model.PublishExamPaper;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublishExamPaperVO {
    private String id;
    private String examPaperId;
    private String memberId;
    private String userId;

    private Integer examCount;      //考试次数
    private Integer lastScore;      //最后一次考试分数
    private Boolean pass;           //是否通过考试
    private Integer highestScore;   //考试获得最高分数
    private Integer lowestScore;    //考试获得最高分数

    private PublishExamPaper.Status status;

    private LocalDateTime startTime;

    private LocalDateTime createTime;
    private Long version;
    private String companyCode;
}
