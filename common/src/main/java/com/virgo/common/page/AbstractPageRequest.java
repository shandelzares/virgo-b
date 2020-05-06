package com.virgo.common.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Data
@EqualsAndHashCode
public abstract class AbstractPageRequest {
    @ApiModelProperty("页码")
    private Integer page = 0;
    @ApiModelProperty("分页大小")
    private Integer size = 20;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }
}
