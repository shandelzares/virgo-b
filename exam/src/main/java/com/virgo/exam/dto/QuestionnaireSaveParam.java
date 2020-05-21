package com.virgo.exam.dto;

import com.virgo.exam.model.QuestionnaireQuestion;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionnaireSaveParam {
    private Long id;
    private String code;
    private String name;
    private String category;
    private LocalDateTime startTime; //考试开始时间
    private LocalDateTime endTime; //考试结束时间
    private List<QuestionnaireQuestion> questions;

}
