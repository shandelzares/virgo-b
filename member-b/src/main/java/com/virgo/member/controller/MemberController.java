package com.virgo.member.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.member.dto.MemberQueryByUserIdsParam;
import com.virgo.member.dto.MemberQueryParam;
import com.virgo.member.model.Member;
import com.virgo.member.service.MemberService;
import com.virgo.member.vo.MemberVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation(value = "通过memberId查询会员详情", notes = "通过memberId查询会员详情")
    @GetMapping("v1/member/{memberId}")
    public ResultData<?> findByMemberId(@PathVariable String memberId) {
        return ResultData.success(memberService.findByMemberId(memberId));
    }

    @ApiOperation(value = "通过memberIds查询会员详情", notes = "通过memberIds查询会员详情")
    @PostMapping("v1/member/ids")
    public ResultData<List<MemberVO>> findByIds(@RequestBody MemberQueryByUserIdsParam param) {
        return ResultData.success(memberService.findByIds(param));
    }

    @ApiOperation(value = "会员分页查询", notes = "会员分页查询")
    @GetMapping("v1/member")
    public ResultData<PageResult<MemberVO>> findPage(MemberQueryParam memberQueryParam) {
        return ResultData.success(memberService.findPage(memberQueryParam));
    }
}
