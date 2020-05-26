package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.config.AppConfig;
import com.funyoo.hqxApp.dao.ArticleDao;
import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.util.ArticleCountPool;
import com.funyoo.hqxApp.vo.ArticleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleReadSevice {

    @Autowired
    public ArticleDao articleDao;

    @Autowired
    AppConfig appConfig;

    @Autowired
    ArticleCountPool articleCountPool;

//    @Value("${hqx.articleOnceReadNum}")
//    private Integer articleOnceReadNum;


    /**
     * 查询模块的文章列表
     * @param part 模块名
     * @param index  尾部下标
     * @return 一次读取的文章列表
     */
    public List<Article> getArticle(String part, Integer index) {
        List<Article> articles;
        if (index == null || index == 0) {
            articles = articleDao.getArticles(part, appConfig.getArticleOnceReadNum());
            //setArticlesFilePath(articles, part);
        } else {
            articles = articleDao.getArticlesByNum(part, index, appConfig.getArticleOnceReadNum());
            //setArticlesFilePath(articles, part);
        }
        return articles;
    }

    /**
     * 查询模块头条文章
     * @param part 模块名
     * @return 文章实例
     */
    public Article getTopArticle(String part) {
        Article article = articleDao.getTopArticle(part);
        //setArticleFilePath(article, part);
        return article;
    }

    /**
     * 查询对应编号文章
     * @param part  文章类目
     * @param artId 文章编号
     * @return
     */
    public Article getArticleById(String part, Integer artId) {
        return articleDao.getArticleById(part, artId);
    }

    /**
     * 查询最新文章
     * @param part
     * @param index
     * @return
     */
    public List<Article> getNewArticle(String part, Integer index) {
        if (index <= 0) {
            return new ArrayList<>();
        }
        return articleDao.getNewArticleByNum(part, index);
    }

    /**
     * 增加浏览量
     * @param articleHelper
     */
    public void counter(ArticleHelper articleHelper) {
        // TODO 或将加redis优化
        articleCountPool.countUp(articleHelper);
    }

    /**
     * 查找文章
     * @param str
     * @return
     */
    public List<Article> search(String str) {
        str = "%" + str + "%";
        return articleDao.search(str);
    }

    /**
     * 给文章引用的文件添加前置路径
     * 此举是方便客户端以访问静态资源服务器来获取资源
     * @param article
     * @param part
     *
     * 2019/12/16 放弃
     */
    private void setArticleFilePath(Article article, String part) {
        article.setPicUrl(appConfig.getStaticUrl() + part + "/image/" + article.getPicUrl());
        article.setHtmlUrl(appConfig.getStaticUrl() + part + "/html/" + article.getHtmlUrl());
    }
    private void setArticlesFilePath(List<Article> articles, String part) {
        for (Article article : articles) {
            setArticleFilePath(article, part);
        }
    }
}
