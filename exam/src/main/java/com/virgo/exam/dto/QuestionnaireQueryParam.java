package com.virgo.exam.dto;

import com.virgo.common.page.AbstractPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionnaireQueryParam extends AbstractPageRequest {
    private String code;
    private String name;
    private String category;
    private String title;
}
