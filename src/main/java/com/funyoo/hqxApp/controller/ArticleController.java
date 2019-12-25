package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.ArticleReadSevice;
import com.funyoo.hqxApp.vo.ArticleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleReadSevice readSevice;

    @RequestMapping("/counter")
    public void counter(String part, Integer id) {
        ArticleHelper articleHelper = new ArticleHelper(part, id);
        readSevice.counter(articleHelper);
    }

    /**
     * 查找文章
     * @param str
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public Result<List<Article>> getNewArticles(String str) {
        List<Article> articles = new ArrayList<>();
        articles = readSevice.search(str);
        return Result.success(articles);
    }
}
