package com.funyoo.hqxApp.util;

import com.funyoo.hqxApp.vo.ArticleHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArticleCountPool {

    public static final Map<ArticleHelper, Integer> countPool = new ConcurrentHashMap<>();

    public static void countUp(ArticleHelper article) {
        Integer now = countPool.get(article);
        if (now == null) {
            countPool.put(article, 1);
        } else {
            countPool.put(article, countPool.get(article) + 1);
        }
    }

    public static void doCount(SetCounter setCounter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                    new Thread().sleep(1000 * 60 * 60 * 12);
                        new Thread().sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setCounter.docount();
                }
            }
        }).start();
    }

    public interface SetCounter {

        public void docount();
    }

    public static void main(String[] args) {
        doCount(new SetCounter() {
            @Override
            public void docount() {
                System.out.println("哈哈哈");
            }
        });
    }
}
