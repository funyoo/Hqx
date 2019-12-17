package com.funyoo.hqxApp.service;

import com.alibaba.fastjson.JSON;
import com.funyoo.hqxApp.config.AppConfig;
import com.funyoo.hqxApp.dao.CollectionDao;
import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.model.CollectionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    @Autowired
    CollectionDao collectionDao;

    @Autowired
    AppConfig appConfig;

    /**
     * 查询用户收藏
     * @param id  用户id 以此确认在哪里查表
     * @param num 索引
     * @return
     */
    public List<CollectionModel> getCollections(Integer id, Integer num) {
        List<CollectionModel> articles;
        // 判断是否存在用户收藏表
        int isExist = collectionDao.tableIsExixt(id);
        if (isExist == 0) {
            return null;
        }
        if (num == null){
            articles = collectionDao.getCollections(id);
        } else if (num == 0) {
            articles = collectionDao.getCollectionsLimit(id, appConfig.getCollectionOnceReadNum());
        } else {
            articles = collectionDao.getCollectionsByNum(id, num, appConfig.getCollectionOnceReadNum());
        }
        return articles;
    }

    /**
     * 收藏文章
     * @param id       用户id
     * @param part     文章类目
     * @param article  文章实例
     * @return
     */
    public boolean doCollect(Integer id, String part, Article article) {
        int isExist = collectionDao.tableIsExixt(id);
        if (isExist == 0) {
            collectionDao.buildTable(id);
        }
        boolean result = collectionDao.doCollect(id, part, article);
        return result;
    }

    /**
     * 判断文章是否已经被收藏过
     * @param uid   用户id
     * @param part  文章类目
     * @param artId 文章id
     * @return
     */
    public boolean isCollected(Integer uid, String part, Integer artId) {
        Integer id = null;
        try {
            id = collectionDao.isCollected(uid, part, artId);
        } catch (Throwable throwable) {
            return false;
        }
        if (id == null || id < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 移除收藏文章
     * @param uid   用户id
     * @param part  文章类目
     * @param artId 文章id
     * @return
     */
    public boolean removeCollect(Integer uid, String part, Integer artId) {
        boolean result = false;
        try {
            result = collectionDao.removeCollect(uid, part, artId);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
        return result;
    }
}
