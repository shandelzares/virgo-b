package com.virgo.exam.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class ExamPaperQuestion {
    @Id
    private Long id;

    @ManyToOne
    private Question question;


}
