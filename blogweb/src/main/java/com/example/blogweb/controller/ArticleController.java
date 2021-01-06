package com.example.blogweb.controller;

import com.example.blogweb.constant.SystemConst;
import com.example.blogweb.service.BlogEngine;
import com.example.common.tool.json.JSONObject;
import com.example.common.tool.resolve.JoinParams;
import com.example.common.tool.resolve.RstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private BlogEngine blogEngine;//忽略编译器报错

    @RequestMapping(value = "/qryArticleList")
    RstData qryArticleList(@RequestBody Map<String, Object> map){
        return blogEngine.invokeService(JoinParams.build(SystemConst.getProjectName(),"BUS0011",map));
    }
}
