package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.model.Article;
import com.funyoo.hqxApp.model.ImgInfo;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.ArticleWriteSevice;
import com.funyoo.hqxApp.service.ResourceService;
import com.funyoo.hqxApp.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

@Controller
@RequestMapping("/hqx_update")
public class ResourceController {

    @Value("${hqx.staticUrl}")
    private String staticUrl;

    @Value("${hqx.absoluteStaticUrl}")
    private String absolutePath;

    @Autowired
    public ResourceService resource;

    @Autowired
    public ArticleWriteSevice writeSevice;

    /**
     * 生成并上传HTML文章 并 保存文件信息至数据库
     * @param html       HTML
     * @param part       模块名
     * @param title      文章标题
     * @param type       文章类型
     * @param recommend  推荐形式
     * @param picUrl     图片路径
     * @return
     */
    @RequestMapping("/html")
    @ResponseBody
    public Result<String> setHtmlUrl(String html, String part, String title, String type, Integer recommend, String picUrl) {
        String fileName = IDUtil.getIdByTimeAnd3Random() + ".html";
        String filePath = absolutePath + part + "/html/" + fileName;
        String fileUrl = staticUrl + part + "/html/" + fileName;
        // result包含的是文件全路径名
        String path = resource.saveFileByBytes(filePath, html.getBytes());
        if (path != null) {
            // 生成文章实例
            Article article = new Article();
            article.setTitle(title);
            article.setType(type);
            article.setRecommend(recommend);
            article.setHtmlUrl(fileUrl);
            article.setPicUrl(picUrl);
            article.setDate(new Date(System.currentTimeMillis()).toString());
            // 保存文章信息至数据库 存储进数据库的url应该是静态资源全url
            boolean write = writeSevice.saveArticle(part, article);
            if (!write) {
                boolean remove = resource.removeFile(filePath);
                // 删除失败 记录 TODO
                return Result.error(CodeMsg.DATABASE_UNKNOW_ERROR);
            }
        }
        return Result.success(fileUrl);
    }

    /**
     * 上传图片
     * @param file  图片文件
     * @param part  模块名称
     * @return      包含图片路径
     */
    @RequestMapping("/image")
    @ResponseBody
    public ImgInfo setImgUrl(@RequestParam("myFile") MultipartFile file, String part) {
        String originalFilename = file.getOriginalFilename();
        String tail = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = IDUtil.getIdByTimeAnd3Random() + tail;
        String filePath = absolutePath + part + "/image/" + fileName;
        String fileUrl = staticUrl + part + "/image/" + fileName;

        String path = null;
        try {
            path = resource.saveFileByBytes(filePath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new ImgInfo(1001, new String[]{"文件上传错误 file update error"});
        }

        String[] values = {fileUrl};

        ImgInfo imgInfo = new ImgInfo(0, values);

        return imgInfo;
    }

    public static void main(String[] args) {
        String url = IDUtil.getIdByTimeAnd3Random() + ".html" + ".jpg";
        System.out.println(url.substring(url.lastIndexOf(".")));
        System.out.println(new Date(System.currentTimeMillis()).toString());
    }

}
