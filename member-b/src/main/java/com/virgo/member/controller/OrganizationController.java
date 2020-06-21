package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.dto.OrganizationSaveParam;
import com.virgo.member.service.OrganizationService;
import com.virgo.member.vo.OrganizationVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    @ApiOperation(value = "分类查询")
    @GetMapping("v1/organization")
    public ResultData<List<OrganizationVO>> findAll() {
        return ResultData.success(organizationService.findAll());
    }

    @ApiOperation(value = "分类新增修改")
    @PostMapping("v1/organization")
    public ResultData<?> save(@Valid @RequestBody OrganizationSaveParam menuSaveParam) {
        organizationService.save(menuSaveParam);
        return ResultData.success();
    }

    @ApiOperation(value = "分类删除")
    @DeleteMapping("v1/organization/{id}")
    public ResultData<?> save(@PathVariable Long id) {
        organizationService.remove(id);
        return ResultData.success();
    }
}
