package com.virgo.member.model;

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
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Type type;

    @ManyToMany
    private List<Role> roles;

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

    public static enum Type {
        /**
         * 公司
         */
        COMPANY,
        /**
         * 部门
         */
        DEPT,
        /**
         * 小组
         */
        GROUP
    }
}
