package com.virgo.member.dto;

import lombok.Data;

@Data
public class CategorySaveParam {
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Integer sort;
    private Boolean enabled;

    /**
     * 分类类型
     * 考试分类、等
     */
    private String type;

}
