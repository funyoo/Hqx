package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.dao.CollectionDao;
import com.funyoo.hqxApp.dao.UserDao;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.util.CaptchaPool;
import com.funyoo.hqxApp.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class RegisterService {

    @Autowired
    UserDao userDao;

    @Autowired
    CollectionDao collectionDao;

    /**
     * 根据 邮箱 注册用户
     * @param mail     邮箱号
     * @param fromPass 密码密文
     * @return 成功返回用户id
     */
    public Result<Boolean> registerUser(@NotNull String mail, @NotNull String fromPass, @NotNull String captcha) {

        String cap = CaptchaPool.getCaptcha(mail);
        if (!captcha.equals(cap)) {
            return Result.error(CodeMsg.CAPTCHA_NOT_TRUE);
        }

        String DBpass = MD5Util.formPassToDBPass(fromPass);
        User user = userDao.getUserByMail(mail);
        if (user != null) {
            return Result.error(CodeMsg.MAIL_ALREADY_REGISTE);
        }

        user = new User();
        user.setMail(mail);
        user.setPassword(DBpass);

        if (userDao.insertUser(user)) {
            CaptchaPool.remove(mail);
            User u = userDao.getUserByMail(mail);
            collectionDao.buildTable(u.getId());
            return Result.success(true);
        }
        return Result.error(CodeMsg.REGISTER_ERR);
    }

}
