package com.virgo.exam.dto;

import com.virgo.common.page.AbstractPageRequest;
import com.virgo.exam.model.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryParam extends AbstractPageRequest {
    private String code;
    private String category;
    private List<Question.Type> type;
    private String level;
    private Integer score;
    private Integer difficult; //难度 1-10
    private String title;
    private String tags;
    private String companyCode;
}
