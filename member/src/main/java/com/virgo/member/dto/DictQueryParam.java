package com.virgo.member.dto;

import com.virgo.common.page.AbstractPageRequest;
import com.virgo.member.model.Dict;
import com.virgo.member.model.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictQueryParam extends AbstractPageRequest {
    private String code;
    private String name;
    private List<Dict.Type> type;
}
