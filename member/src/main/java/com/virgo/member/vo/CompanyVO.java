package com.virgo.member.vo;

import com.virgo.member.model.Company;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyVO {
    private Long id;
    private String name;
    private String shortName;
    private String brandName;
    private String code;
    private String telPhone;
    private String phone;
    private String contactPersonPhone;
    private String address;
    private String contactPerson;   //联系人
    private String juridicalPerson; //法人
    private Company.Status status;  //状态
    private Boolean authenticated;//实名认证
    private Company.AuthenticatedStatus authenticatedStatus;
    private Company.AccountType accountType;
    private String creator;
    private String revisor;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
