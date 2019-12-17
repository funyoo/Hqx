package com.funyoo.hqxApp.controller;

import com.alibaba.fastjson.JSON;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hqx_login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(String mail, String password) {
        User result = loginService.login("", mail, password);
        if (result != null) {
            result.setPassword(null);
            String userInfo = JSON.toJSONString(result);
            return Result.success(userInfo);
        }
        return Result.error(CodeMsg.ID_OR_PASSWORD_ERR);
    }
}
