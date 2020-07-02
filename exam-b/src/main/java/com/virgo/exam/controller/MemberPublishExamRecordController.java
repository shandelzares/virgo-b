package com.virgo.exam.controller;

import com.virgo.common.response.ResultData;
import com.virgo.exam.service.PublishExamRecordService;
import com.virgo.exam.vo.ExamRecordVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MemberPublishExamRecordController {
    @Resource
    private PublishExamRecordService publishExamRecordService;


    @ApiOperation(value = "考试人员列表", notes = "考试人员列表")
    @GetMapping("v1/member/exam/publish/record/{publishExamPaperId}")
    public ResultData<List<ExamRecordVO>> findPage(@PathVariable String publishExamPaperId) {
        return ResultData.success(publishExamRecordService.findByPublishExamPaperId(publishExamPaperId));
    }
}
