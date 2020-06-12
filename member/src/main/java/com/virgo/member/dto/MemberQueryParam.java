package com.virgo.member.dto;

import com.virgo.common.page.AbstractPageRequest;
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
public class MemberQueryParam extends AbstractPageRequest {
    @ApiModelProperty("会员id")
    private String memberId;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("组织id")
    private String organizationId;
    @ApiModelProperty("性别")
    private Member.Sex sex;
    @ApiModelProperty("公司code")
    private String companyCode;
    @ApiModelProperty("会员类型")
    private List<Member.Type> type;
    @ApiModelProperty("是否删除")
    private Boolean deleted;
    @ApiModelProperty("会员状态")
    private List<Member.Status> status;
    @ApiModelProperty("出生日期开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdayStart;
    @ApiModelProperty("出生日期结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdayEnd;
    @ApiModelProperty("创建时间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart;
    @ApiModelProperty("创建时间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;
}
