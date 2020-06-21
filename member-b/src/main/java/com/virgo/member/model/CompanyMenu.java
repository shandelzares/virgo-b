package com.virgo.member.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class CompanyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Menu menu;
    private String name;
    private Integer sort;
    private String icon;
    private Boolean keepAlive;

    private Boolean deleted;

    private Boolean isShow;

    @CreatedBy
    private String creator;
    @LastModifiedBy
    private String revisor;
    private LocalDateTime updateTime;
    @CreatedDate
    private LocalDateTime createTime;
    @Version
    private Long version;

    private String companyCode;
}
