package com.virgo.exam.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.exam.dto.QuestionQueryParam;
import com.virgo.exam.dto.QuestionSaveParam;
import com.virgo.exam.service.QuestionService;
import com.virgo.exam.vo.QuestionVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class QuestionController {
    @Resource
    private QuestionService questionService;


    @ApiOperation(value = "题库列表", notes = "题库列表")
    @GetMapping("v1/question")
    public ResultData<PageResult<QuestionVO>> findPage(@Valid QuestionQueryParam questionQueryParam) {
        return ResultData.success(questionService.findPage(questionQueryParam));
    }

    @ApiOperation(value = "题库保存", notes = "题库保存")
    @PostMapping("v1/question")
    public ResultData<?> save(@Valid @RequestBody QuestionSaveParam questionSaveParam) {
        questionService.save(questionSaveParam);
        return ResultData.success();
    }

    @ApiOperation(value = "题库删除", notes = "题库删除")
    @DeleteMapping("v1/question/{id}")
    public ResultData<?> remove(@PathVariable String id) {
        questionService.remove(id);
        return ResultData.success();
    }
}
