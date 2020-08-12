package com.ue.util;

import lombok.Data;

@Data
public class Result {
    //状态码
    private int status;
    //消息值
    private String message;
    //数据
    private Object data;

    public static Result ok() {
        Result r = new Result();
        r.setStatus(200);
        r.setMessage("成功");
        return r;
    }

    public static Result ok(Object data) {
        Result r = ok();
        r.setData(data);
        return r;
    }

    public static Result error(String message) {
        Result r = new Result();
        r.setStatus(500);
        r.setMessage(message);
        return r;
    }
}
