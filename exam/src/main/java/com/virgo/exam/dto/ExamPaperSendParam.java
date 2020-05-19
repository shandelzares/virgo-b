package com.virgo.exam.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperSendParam {
    private Long examPaperId;
    private List<Long> userIds;
    private Type type;

    public static enum Type {
        ASSIGN, COMMON, EXERCISE
    }
}
