package com.funyoo.hqxApp.util;

import java.util.Random;

public class IDUtil {

    public static String getIdByTimeAnd3Random() {
        return System.currentTimeMillis() + "" + new Random().nextInt(999);
    }

    public static void main(String[] args) {
        System.out.println(getIdByTimeAnd3Random());
    }
}
