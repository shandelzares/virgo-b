package com.virgo.exam.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    /**
     * 分类
     */
    private String category;
    private LocalDateTime startTime; //考试开始时间
    private LocalDateTime endTime; //考试结束时间

    @OneToMany
    @Fetch(FetchMode.JOIN)
    private List<QuestionnaireQuestion> questions;

    @CreatedBy
    private String creator;//创建人code
    @LastModifiedBy
    private String revisor;//更新人code
    private LocalDateTime updateTime;
    @CreatedDate
    private LocalDateTime createTime;
    @Version
    private Long version;
    private String companyCode;
    private Status status;

    public static enum Status {
        /**
         * 草稿
         */
        DRAFT,
        /**
         * 发布
         */
        PUBLISHED,
        /**
         * 删除
         */
        DELETED
    }
}
