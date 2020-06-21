package com.virgo.member.controller;

import com.virgo.common.response.ResultData;
import com.virgo.member.service.CloudStoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UploadController {
    @Resource
    private CloudStoreService cloudStoreService;

    @GetMapping("/v1/upload/token")
    public ResultData<?> getToken(String key) {
        return ResultData.success(cloudStoreService.getToken(key));
    }

}
