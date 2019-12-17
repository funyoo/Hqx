package com.funyoo.hqxApp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaUtil {

    public static final char[] chars = new char[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static String getCaptcha(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        int codeNum = chars.length;
        for (int i = 0; i < length; i++) {
            code.append(chars[random.nextInt(codeNum)]);
        }
        return code.toString();
    }

    public static String getCaptcha() {
        return getCaptcha(6);
    }


}
