package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.service.CompanyService;
import com.virgo.member.vo.CompanyVO;
import com.virgo.member.vo.MenuVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @ApiOperation(value = "公司全量查询")
    @GetMapping("v1/company")
    public ResultData<List<CompanyVO>> findAll() {
        return ResultData.success(companyService.findAll());
    }
}
