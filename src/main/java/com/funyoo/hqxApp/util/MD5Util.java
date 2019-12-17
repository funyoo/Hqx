package com.funyoo.hqxApp.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "97funyoo23";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(5) + salt.charAt(0) + inputPass + salt.charAt(4) + salt.charAt(7);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        // 取余运算 防止下标越界
        int length = salt.length();
        String str = "" + salt.charAt(5 % length) + salt.charAt(0)
                + formPass + salt.charAt(4 % length) + salt.charAt(7 % length);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass) {
        return formPassToDBPass(formPass, salt);
    }

    public static String inputPassToDBPass(String inputPass, String saltDB) {
        return formPassToDBPass(inputPassToFormPass(inputPass), saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(inputPassToDBPass("123456", "as12df"));
    }
}
