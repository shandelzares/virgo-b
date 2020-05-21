package com.virgo.exam.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class QuestionnaireQuestionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean isCorrect;
    private Integer score;
}
