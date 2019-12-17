package com.funyoo.hqxApp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userMsg")
public class UserMsgController {

    @Autowired
    public UserService userService;

    /**
     * 更新用户信息
     * @param userMsgJson 用户信息json
     * @return
     */
    @RequestMapping("/update")
    public Result<Boolean> updateUserMsg(String userMsgJson) {
        User user = JSON.parseObject(userMsgJson, User.class);
        boolean result = userService.updateUser(user);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error(CodeMsg.DATABASE_UNKNOW_ERROR);
        }
    }
}
