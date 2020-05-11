package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.dto.MenuSaveParam;
import com.virgo.member.service.MenuService;
import com.virgo.member.vo.MenuVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    @ApiOperation(value = "菜单全量查询", notes = "菜单全量查询")
    @GetMapping("v1/menu")
    public ResultData<List<MenuVO>> findAll() {
        return ResultData.success(menuService.findAll());
    }
    @ApiOperation(value = "菜单新增修改", notes = "菜单新增修改")
    @PostMapping("v1/menu")
    public ResultData<?> save(@Valid @RequestBody MenuSaveParam menuSaveParam) {
        menuService.save(menuSaveParam);
        return ResultData.success();
    }
}
