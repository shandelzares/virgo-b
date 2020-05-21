package com.virgo.exam.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.exam.dto.*;
import com.virgo.exam.service.QuestionnaireService;
import com.virgo.exam.vo.QuestionnaireVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;

public class QuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;

    @ApiOperation(value = "试卷列表", notes = "试卷列表")
    @GetMapping("v1/questionnaire")
    public ResultData<PageResult<QuestionnaireVO>> findPage(@Valid QuestionnaireQueryParam questionnaireQueryParam) {
        return ResultData.success(questionnaireService.findPage(questionnaireQueryParam));
    }


    @ApiOperation(value = "试卷保存", notes = "试卷保存")
    @PostMapping("v1/questionnaire")
    public ResultData<?> save(@Valid @RequestBody QuestionnaireSaveParam questionSaveParam) {
        questionnaireService.save(questionSaveParam);
        return ResultData.success();
    }


    @ApiOperation(value = "试卷发送", notes = "试卷发送")
    @PostMapping("v1/questionnaire/send")
    public ResultData<?> send(@Valid @RequestBody QuestionnaireSendParam questionnaireSendParam) {
        questionnaireService.send(questionnaireSendParam);
        return ResultData.success();
    }
}
