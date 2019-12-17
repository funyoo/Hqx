package com.funyoo.hqxApp.util;

import java.util.HashMap;
import java.util.Map;

public class CaptchaPool {

    // key: email    value: 验证码
    public static final Map<String, String> captchas = new HashMap<>();

    public static String getCaptcha(String mail) {
        return captchas.get(mail);
    }

    public static void setCaptchas(String mail, String captcha) {
        captchas.put(mail, captcha);
    }

    public static void remove(String mail) {
        captchas.remove(mail);
    }

    public static void clear() {
        captchas.clear();
    }
}
