package com.virgo.member.controller;

import com.virgo.common.page.PageResult;
import com.virgo.common.response.ResultData;
import com.virgo.member.dto.DictQueryParam;
import com.virgo.member.dto.DictSaveParam;
import com.virgo.member.dto.MenuSaveParam;
import com.virgo.member.service.DictService;
import com.virgo.member.vo.DictVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class DictController {
    @Resource
    private DictService dictService;

    @ApiOperation(value = "字典查询", notes = "字典查询")
    @GetMapping("v1/dict")
    public ResultData<PageResult<DictVO>> findDictPage(@Valid DictQueryParam dictQueryParam) {
        return ResultData.success(dictService.findDictPage(dictQueryParam));
    }

    @ApiOperation(value = "字典新增修改", notes = "字典新增修改")
    @PostMapping("v1/dict")
    public ResultData<?> save(@Valid @RequestBody DictSaveParam dictSaveParam) {
        dictService.save(dictSaveParam);
        return ResultData.success();
    }
}
