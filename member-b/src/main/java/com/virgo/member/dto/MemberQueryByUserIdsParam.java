package com.virgo.member.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberQueryByUserIdsParam {
    private List<Long> userIds;
}
