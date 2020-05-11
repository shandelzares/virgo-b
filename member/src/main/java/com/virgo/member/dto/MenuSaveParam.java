package com.virgo.member.dto;

import com.virgo.member.model.Menu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Version;
import java.time.LocalDateTime;

@Data
@ApiModel
public class MenuSaveParam {

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

    /**
     * 是否显示
     */
    private Boolean isShow;

}
