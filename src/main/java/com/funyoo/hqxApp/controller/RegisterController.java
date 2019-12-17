package com.funyoo.hqxApp.controller;

import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.service.EmailService;
import com.funyoo.hqxApp.service.RegisterService;
import com.funyoo.hqxApp.util.CaptchaPool;
import com.funyoo.hqxApp.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Random;

@Controller
@RequestMapping("/hqx_register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    EmailService mailService;

    /**
     * 注册(接口)
     * @param mail     用户邮箱
     * @param captcha  验证码
     * @param password 密码(加密后)
     * @return
     */
    @RequestMapping("/do_register")
    @ResponseBody
    public Result<Boolean> register(String mail, String captcha, String password) {
        return registerService.registerUser(mail, password, captcha);
    }

    /**
     * 获取验证码
     * @param mail 用户邮箱号
     * @return
     */
    @RequestMapping("/get_captcha")
    @ResponseBody
    public Result<Boolean> getCaptcha(String mail) {
        if (mail == null || mail.indexOf("@") == -1) {
            return Result.error(CodeMsg.MAIL_NOT_GOOD);
        }

        //String captcha = CaptchaUtil.getCaptcha();
        String captcha = String.valueOf(new Random().nextInt(1000000));
        // TODO 这里有坑 存储验证码而不验证,易受攻击 建议用redis缓存
        CaptchaPool.setCaptchas(mail, captcha);
        try {
            mailService.sendCaptcha(mail, captcha);
        } catch (Exception e) {
            e.printStackTrace();
            CaptchaPool.remove(mail);
            return Result.error(CodeMsg.SEND_MAIL_ERR);
        }

        return Result.success(true);
    }

    public static void main(String[] args) throws IOException {
//        RegisterController controller = new RegisterController();
//        controller.mailService = new EmailService();
//        controller.getCaptcha("401476968@qq.com");
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
    }
}
