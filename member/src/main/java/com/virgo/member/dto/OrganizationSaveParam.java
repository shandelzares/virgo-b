package com.virgo.member.dto;

import lombok.Data;

@Data
public class OrganizationSaveParam {
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Integer sort;
    private Boolean enabled;
    private String type;
}
