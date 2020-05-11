package com.virgo.member.vo;

import com.virgo.member.model.Dict;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DictVO <T>{
    private Long id;
    private String code;
    private String name;
    private String desc;

    private Dict.Type type;

    /**
     * todo 直接使用Dict 实体
     */
    private T value;

    private String creator;
    private String revisor;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;

}
