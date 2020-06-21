package com.virgo.exam.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.exam.dto.ExamPaperQueryParam;
import com.virgo.exam.dto.memberExamRecordServiceQueryParam;
import com.virgo.exam.service.ExamPaperService;
import com.virgo.exam.service.MemberExamRecordService;
import com.virgo.exam.vo.ExamPaperVO;
import com.virgo.exam.vo.PublishExamPaperVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class MemberExamRecordController {
    @Resource
    private MemberExamRecordService memberExamRecordService;


    @ApiOperation(value = "考试人员列表", notes = "考试人员列表")
    @GetMapping("v1/member/exam/record")
    public ResultData<PageResult<PublishExamPaperVO>> findPage(@Valid memberExamRecordServiceQueryParam questionQueryParam) {
        return ResultData.success(memberExamRecordService.findPage(questionQueryParam));
    }
}
