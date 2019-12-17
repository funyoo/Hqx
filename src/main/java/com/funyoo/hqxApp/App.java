package com.funyoo.hqxApp;

import com.funyoo.hqxApp.util.ArticleCountPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
//        ArticleCountPool.doCount(new ArticleCountPool.SetCounter() {
//            @Override
//            public void docount() {
//                System.out.println("哈哈哈");
//            }
//        });
    }
}
