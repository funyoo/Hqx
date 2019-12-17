package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.ArticleReadSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hqx_newsview")
public class AppNewsViewController {

    @Autowired
    ArticleReadSevice viewSevices;

    /**
     * 查询相关模块文章列表
     * @param part 模块名称
     * @param index  尾部下标  查询小于改下标的文章若干篇
     * @return 文章列表
     */
    @RequestMapping("/articles")
    @ResponseBody
    public Result<List<Article>> getArticles(String part, Integer index) {
        //查询相关模块文章列表，返回list
        List<Article> articles;
        articles = viewSevices.getArticle(part, index);
        if (articles == null || articles.size() == 0) {
            return Result.error(CodeMsg.NO_ARTICLE_DATA);
        }
        return Result.success(articles);
    }

    /**
     * 查询模块首页文章(带图片)
     * @param part 模块名称
     * @return
     */
    @RequestMapping("/top")
    @ResponseBody
    public Result<Article> getTopArticle(String part) {
        //查询首页头条文章，带图片，返回文章实例
        Article article;
        article = viewSevices.getTopArticle(part);
        if (article == null || article.getPicUrl().isEmpty()) {
            return Result.error(CodeMsg.GET_TOP_VIEW_ERR);
        }
        return Result.success(article);
    }
}
