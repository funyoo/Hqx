package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.model.CollectionModel;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.ArticleReadSevice;
import com.funyoo.hqxApp.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    public CollectionService collectionService;

    @Autowired
    public ArticleReadSevice articleService;

    /**
     * 查询收藏列表
     * @param id  用户id 以此确定表
     * @param num 索引
     * @return
     */
    @RequestMapping("/list")
    public Result<List> getCollections(Integer id, Integer num) {
        List<CollectionModel> articles;
        articles = collectionService.getCollections(id, num);
        if (articles == null || articles.size() == 0) {
            return Result.error(CodeMsg.NO_COLLECTION_DATA);
        }
        return Result.success(articles);
    }

    /**
     * 收藏文章
     * @param uid   用户id
     * @param part  文章类目
     * @param artId 文章编号
     * @return
     */
    @RequestMapping("/doCollect")
    public Result<Boolean> doCollect(Integer uid, String part, Integer artId) {
        Article article = articleService.getArticleById(part, artId);
        if (article == null) {
            return Result.error(CodeMsg.NO_COLLECT_TARGET);
        }
        boolean result = collectionService.doCollect(uid, part, article);
        if (result) {
            return Result.success(result);
        } else {
            return Result.error(CodeMsg.DATABASE_UNKNOW_ERROR);
        }
    }

    /**
     * 取消收藏文章
     * @param uid   用户id
     * @param part  文章类目
     * @param artId 文章编号
     * @return
     */
    @RequestMapping("/removeCollect")
    public Result<Boolean> removeCollect(Integer uid, String part, Integer artId) {
        boolean result = collectionService.removeCollect(uid, part, artId);
        if (result) {
            return Result.success(result);
        } else {
            return Result.error(CodeMsg.DATABASE_UNKNOW_ERROR);
        }
    }

    /**
     * 判断文章是否已经被收藏
     * @param uid    用户id
     * @param part   文章类目
     * @param artId  文章id
     * @return
     */
    @RequestMapping("/isCollected")
    public Result<Boolean> IsCollected(Integer uid, String part, Integer artId) {
        boolean result = collectionService.isCollected(uid, part, artId);
        return Result.success(result);
    }
}
