package com.virgo.exam.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class QuestionnaireQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;//选项

    private Long questionId;

    private Boolean required;
    private Question.Type type;
    private String content;

    @OneToMany
    private List<QuestionnaireQuestionItem> answer;
}
