package com.example.blogengine.service;

import com.example.blogengine.mapper.ArticleMapper;
import com.example.blogengine.pojo.Article;
import com.example.blogengine.pojo.ArticleExample;
import com.example.common.tool.date.SysDate;
import com.example.common.tool.json.JSONObject;
import com.example.common.tool.page.PageBean;
import com.example.common.tool.resolve.ResultStatus;
import com.example.common.tool.resolve.RstData;
import com.example.common.tool.resolve.ServiceType;
import com.example.common.tool.string.StringUtil;
import com.example.common.tool.validate.ObjectCensor;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("BUS001")
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    /**
     * desc 查询文章列表
        逻辑简要说明：
     * @param param
        {}

        入参说明：

        @return

        返回值说明：
     */
    @ServiceType(value = "BUS0011", desc = "查询文章列表")
    public RstData qryArticleList(String param){
        JSONObject jsonParam = JSONObject.fromObject(param);
        Integer pageSize = Integer.valueOf(StringUtil.getJSONObjectKeyVal(jsonParam,"pageSize"));
        Integer currentPage = Integer.valueOf(StringUtil.getJSONObjectKeyVal(jsonParam,"currentPage"));
        Timestamp timestamp = SysDate.getSysDate();

        ArticleExample articleExample = new ArticleExample();
        articleExample.setDistinct(true);//去重
        articleExample.createCriteria().andAuthorEqualTo("tzs");
        PageHelper.startPage(currentPage, pageSize);
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        int countNums = articleMapper.countByExample(articleExample);
        PageBean<Article> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(articleList);

        return new RstData(){{
            setData(pageData);
        }};
    }

}
