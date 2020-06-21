package com.virgo.exam.dto;

import com.virgo.common.page.AbstractPageRequest;
import com.virgo.exam.model.PublishExamPaper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class memberExamRecordServiceQueryParam extends AbstractPageRequest {
    private String examPaperId;
    private String memberId;
    private String userId;

    private Integer examCount;      //考试次数
    private Integer lastScore;      //最后一次考试分数
    private Boolean pass;           //是否通过考试
    private Integer highestScore;   //考试获得最高分数
    private Integer lowestScore;    //考试获得最高分数

    private PublishExamPaper.Status status;
}