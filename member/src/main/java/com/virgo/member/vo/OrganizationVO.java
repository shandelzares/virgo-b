package com.virgo.member.vo;

import com.virgo.member.model.Organization;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrganizationVO {
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Organization.Type type;
    private List<OrganizationVO> children;
    private String creator;
    private String revisor;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
