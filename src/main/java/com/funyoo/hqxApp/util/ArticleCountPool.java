package com.funyoo.hqxApp.util;

import com.funyoo.hqxApp.dao.ArticleDao;
import com.funyoo.hqxApp.vo.ArticleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文章访问量池
 */
@Component
public class ArticleCountPool {

    @Autowired
    ArticleDao articleDao;

    public final Map<ArticleHelper, Integer> countPool;
    private Thread counter;

    ArticleCountPool () {
        countPool = new ConcurrentHashMap<>();
        counter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        counter.sleep(1000 * 60 * 60 * 6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doCount();
                }
            }
        });
        counter.start();
    }

    public void countUp(ArticleHelper article) {
        Integer now = countPool.get(article);
        if (now == null) {
            countPool.put(article, 1);
        } else {
            countPool.put(article, countPool.get(article) + 1);
        }
    }

    public void doCount() {
        for (ArticleHelper key : countPool.keySet()) {
            int count = countPool.get(key);
            articleDao.count(key.getPart(), key.getId(), count);
            countPool.remove(key);
        }
    }

}
