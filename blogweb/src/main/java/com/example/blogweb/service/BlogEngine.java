package com.example.blogweb.service;

import com.example.common.tool.resolve.JoinParams;
import com.example.common.tool.resolve.RstData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="blogengine")
public interface BlogEngine{
    @RequestMapping(value = "/invoke/distribute",consumes = "application/json")
    RstData invokeService(@RequestBody JoinParams joinParams);
}
