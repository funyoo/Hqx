package com.funyoo.hqxApp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CaptchaPool {

    // key: email    value: 验证码
    public static final Map<String, Captcha> captchas = new ConcurrentHashMap<>();
    private static int size = 1000;
    private static int sizeStep = 1000;

    public static String getCaptcha(String mail) {
        return captchas.get(mail).value;
    }

    public static void setCaptchas(String mail, String captcha) {
        // 当验证码存储空间大于最大空间时，触发清理，清理过期
        if (captchas.keySet().size() >= size) {
            clearOld();
            if (captchas.keySet().size() >= size) {
                size += sizeStep;
            }
        }
        captchas.put(mail, new Captcha(System.currentTimeMillis(), captcha));
        // 空间缩容
        if (captchas.keySet().size() < sizeStep) {
            size = sizeStep;
        }
    }

    public static void remove(String mail) {
        captchas.remove(mail);
    }

    /**
     * 清理超时验证码
     */
    private static void clearOld() {
        long now = System.currentTimeMillis();
        for (String key : captchas.keySet()) {
            if (now > captchas.get(key).time + 10 * 60 * 60) {
                captchas.remove(key);
            }
        }
    }

    public static void clear() {
        captchas.clear();
    }

    private static class Captcha {
        Long time;
        String value;

        Captcha (Long time, String captcha) {
            this.time = time;
            this.value = value;
        }
    }
}
