package com.virgo.member.vo;

import com.virgo.member.model.Menu;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;

@Data
public class MenuVO {
    private Long id;
    private String name;
    private Long parentId;
    private Menu.Type type;
    private String component;

    /**
     * 前端路由
     */
    private String path;
    private Integer sort;
    private String icon;
    private String tag;
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

    private String companyCode;

    private List<MenuVO> children;

}
