package com.funyoo.hqxApp.result;

public class CodeMsg {

    public static final CodeMsg NO_ARTICLE_DATA = new CodeMsg(500404, "没有找到数据！");
    public static final CodeMsg GET_TOP_VIEW_ERR = new CodeMsg(500405, "没有找到头条数据！");

    public static final CodeMsg NO_COLLECTION_DATA = new CodeMsg(500504, "没有收藏文章");

    public static final CodeMsg ID_OR_PASSWORD_ERR = new CodeMsg(500600, "账号或密码错误");
    public static final CodeMsg REGISTER_ERR = new CodeMsg(500610, "注册用户时出错");
    public static final CodeMsg MAIL_ALREADY_REGISTE = new CodeMsg(500620, "邮箱已注册");
    public static final CodeMsg CAPTCHA_NOT_TRUE = new CodeMsg(500630, "验证码不正确");
    public static final CodeMsg SEND_MAIL_ERR = new CodeMsg(500640, "发送验证码时邮箱出错");
    public static final CodeMsg MAIL_NOT_GOOD = new CodeMsg(500650, "无效邮箱");


    public static final CodeMsg SAVE_IO_ERROR = new CodeMsg(500700, "保存时IO异常");
    public static final CodeMsg CLOSE_IO_ERROR = new CodeMsg(500710, "关闭流时异常");


    public static final CodeMsg DATABASE_UNKNOW_ERROR = new CodeMsg(500800, "数据库异常");
    public static final CodeMsg NO_DATA_ERROR = new CodeMsg(500810, "没有查询到数据");
    public static final CodeMsg NO_COLLECT_TARGET = new CodeMsg(500900, "没有找到目标文章");


    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
