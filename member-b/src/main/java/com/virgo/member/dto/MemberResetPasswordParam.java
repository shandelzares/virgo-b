package com.virgo.member.dto;

import lombok.Data;

@Data
public class MemberResetPasswordParam {
    private Long userId;
    private String password;
}
