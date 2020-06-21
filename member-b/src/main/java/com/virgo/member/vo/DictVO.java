package com.virgo.member.vo;

import com.virgo.member.model.Dict;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class DictVO {
    private Long id;
    private String code;
    private Boolean isTop;
    private String name;
    private String description;

    private Dict.Type type;

    private Dict.FormatType formatType;

    /**
     * todo 直接使用Dict 实体
     */
    private String value;

    private String creator;
    private String revisor;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String companyCode;
    private List<DictVO> children;

}
