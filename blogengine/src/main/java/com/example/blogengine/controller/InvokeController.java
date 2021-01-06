package com.example.blogengine.controller;

import com.example.common.component.BeanFactoryHelper;
import com.example.common.tool.resolve.JoinParams;
import com.example.common.tool.resolve.MultiServiceMethodAdapter;
import com.example.common.tool.resolve.RstData;
import com.example.common.tool.resolve.ServiceHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/invoke")
public class InvokeController implements ServiceHandler{

    @RequestMapping(value = "/distribute" , method = RequestMethod.POST)
    @ResponseBody
    public RstData distribute(@RequestBody JoinParams joinParams) {
        RstData rstData = MultiServiceMethodAdapter.invoke(joinParams, BeanFactoryHelper.getBeanfactory());
        return rstData;
    }
}
