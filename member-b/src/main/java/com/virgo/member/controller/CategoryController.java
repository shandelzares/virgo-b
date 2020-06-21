package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.dto.CategorySaveParam;
import com.virgo.member.service.CategoryService;
import com.virgo.member.vo.CategoryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "分类查询")
    @GetMapping("v1/category")
    public ResultData<List<CategoryVO>> findAll() {
        return ResultData.success(categoryService.findAll());
    }

    @ApiOperation(value = "分类新增修改")
    @PostMapping("v1/category")
    public ResultData<?> save(@Valid @RequestBody CategorySaveParam menuSaveParam) {
        categoryService.save(menuSaveParam);
        return ResultData.success();
    }

    @ApiOperation(value = "分类删除")
    @DeleteMapping("v1/category/{id}")
    public ResultData<?> save(@PathVariable Long id) {
        categoryService.remove(id);
        return ResultData.success();
    }
}
