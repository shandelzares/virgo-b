package com.virgo.exam.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.exam.dto.ExamPaperQueryParam;
import com.virgo.exam.dto.ExamPaperSaveParam;
import com.virgo.exam.dto.ExamPaperSendParam;
import com.virgo.exam.service.ExamPaperService;
import com.virgo.exam.vo.ExamPaperVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class ExamPaperController {
    @Resource
    private ExamPaperService examPaperService;


    @ApiOperation(value = "试卷列表", notes = "试卷列表")
    @GetMapping("v1/exam/paper")
    public ResultData<PageResult<ExamPaperVO>> findPage(@Valid ExamPaperQueryParam questionQueryParam) {
        return ResultData.success(examPaperService.findPage(questionQueryParam));
    }

    @ApiOperation(value = "试卷详情", notes = "试卷详情")
    @GetMapping("v1/exam/paper/{id}")
    public ResultData<ExamPaperVO> detail( @PathVariable String id) {
        return ResultData.success(examPaperService.detail(id));
    }

    @ApiOperation(value = "试卷保存", notes = "试卷保存")
    @PostMapping("v1/exam/paper")
    public ResultData<?> save(@Valid @RequestBody ExamPaperSaveParam questionSaveParam) {
        examPaperService.save(questionSaveParam);
        return ResultData.success();
    }

    @ApiOperation(value = "试卷删除", notes = "试卷删除")
    @DeleteMapping("v1/exam/paper/{id}")
    public ResultData<?> remove(@PathVariable String id) {
        examPaperService.remove(id);
        return ResultData.success();
    }

    @ApiOperation(value = "试卷发送", notes = "试卷发送")
    @PostMapping("v1/exam/paper/send")
    public ResultData<?> send(@Valid @RequestBody ExamPaperSendParam questionSaveParam) {
        examPaperService.send(questionSaveParam);
        return ResultData.success();
    }

}
