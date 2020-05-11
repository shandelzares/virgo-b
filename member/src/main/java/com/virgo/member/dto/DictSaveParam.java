package com.virgo.member.dto;

import com.virgo.member.model.Dict;
import lombok.Data;

@Data
public class DictSaveParam{

    private String code;

    private String name;

    private String desc;

    private Dict.Type type;

    private String value;

    private Dict.FormatType formatType;

}
