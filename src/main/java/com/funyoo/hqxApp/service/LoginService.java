package com.funyoo.hqxApp.service;

import com.funyoo.hqxApp.dao.UserDao;
import com.funyoo.hqxApp.model.User;
import com.funyoo.hqxApp.result.CodeMsg;
import com.funyoo.hqxApp.result.Result;
import com.funyoo.hqxApp.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    /**
     * 登录
     * @param type      类型 候选项
     * @param mail      邮箱号码
     * @param password  密码密文
     * @return
     */
    public User login(String type, String mail, String password) {
        User user = userDao.getUserByMail(mail);

        if (user != null) {
            String DBpass = user.getPassword();
            String pass = MD5Util.formPassToDBPass(password);

            if (DBpass.equals(pass)) {
                System.out.println(user);
                return user;
            }
        }

        return null;
    }
}
