package com.funyoo.hqxApp.controller;

import com.alibaba.fastjson.JSON;
import com.funyoo.hqxApp.aspect.LogAspect;
import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.ArticleReadSevice;
import com.funyoo.hqxApp.util.cachePool.CacheKeys;
import com.funyoo.hqxApp.util.cachePool.CachePool;
import com.funyoo.hqxApp.util.cachePool.CachePoolInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Autowired
    CachePoolInterface cachePool;

    private Logger logger = LogManager.getLogger(AppNewsViewController.class);

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
        String listStr;
        String key = CacheKeys.ARTICLE_LIST + part + index;
        if ((listStr = (String) cachePool.get(key)) != null) {
            // 内存缓存有数据
            return Result.success(JSON.parseObject(listStr, List.class));
        }

        logger.info("[DO_SQL]" + "[getArticles] [" + part + ", " + index + "]");
        articles = viewSevices.getArticle(part, index);
        if (articles == null || articles.size() == 0) {
            return Result.error(CodeMsg.NO_ARTICLE_DATA);
        }
        cachePool.put(key, JSON.toJSONString(articles));
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
        String topStr;
        String key = CacheKeys.ARTICLE_TOP + part;
        if ((topStr = (String) cachePool.get(key)) != null) {
            return Result.success(JSON.parseObject(topStr, Article.class));
        }

        logger.info("[DO_SQL]" + "[getTopArticle] [" + part + "]");
        article = viewSevices.getTopArticle(part);
        if (article == null || article.getPicUrl().isEmpty()) {
            return Result.error(CodeMsg.GET_TOP_VIEW_ERR);
        }
        cachePool.put(key, JSON.toJSONString(article));
        return Result.success(article);
    }
}
