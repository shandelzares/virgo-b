package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.dto.MemberQueryParam;
import com.virgo.member.service.MemberService;
import com.virgo.member.vo.MemberVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation(value = "通过memberId查询会员详情", notes = "通过memberId查询会员详情")
    @GetMapping("v1/member/{memberId}")
    public ResultData<?> findByMemberId(@PathVariable String memberId) {
        return ResultData.success(memberService.findByMemberId(memberId));
    }

    @ApiOperation(value = "会员分页查询", notes = "会员分页查询")
    @GetMapping("v1/member")
    public ResultData<Page<MemberVO>> findPage(MemberQueryParam memberQueryParam) {
        return ResultData.success(memberService.findPage(memberQueryParam));
    }
}
