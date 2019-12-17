package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.service.ArticleReadSevice;
import com.funyoo.hqxApp.vo.ArticleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
