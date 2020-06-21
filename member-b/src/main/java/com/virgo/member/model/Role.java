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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * 组织id
     */
    private String orgId;

    private Type type;

    private String description;

    private Boolean deleted;
    
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

    @ManyToMany
    private List<Menu> menus;

    public static enum Type {
        /**
         * 菜单
         */
        MENU,
        /**
         * 按钮
         */
        BUTTON,
    }
}
