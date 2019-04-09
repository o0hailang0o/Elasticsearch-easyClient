package com.demo.controller;

import com.demo.model.Result;

/**
 * @author liujian on 2019/1/9.
 */
public class ResultUtils {

    public static Result success(){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("请求成功");
        result.setData(1);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("请求失败");
        result.setData(0);
        return result;
    }
}
