package com.virgo.member.model;

import lombok.Data;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    private Type type;
    private String tag;
    private String component;
    /**
     * 前端路由
     */
    private String path;
    private Integer sort;
    private String icon;
    /**
     * 路由缓存
     */
    private Boolean keepAlive;

    private Boolean deleted;

    /**
     * 是否显示
     */
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
