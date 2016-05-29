package com.ruyuapp.extractor.controller;

import com.alibaba.fastjson.JSON;
import com.ruyuapp.extractor.selector.JsonPathSelector;

import java.util.List;

/**
 * Json 数据抽取控制器
 * @author letcheng letcheng@ruyuapp.com
 */
public class Json extends PlainText {

    private Json(String text) {
        super(text);
    }

    public static Json create(String text){
        return new Json(text);
    }

    //集成fastjson转换对象
    public <T> T toObject(Class<T> clazz) {
        if (getFirstSourceText() == null) {
            return null;
        }
        return JSON.parseObject(getFirstSourceText(), clazz);
    }

    //集成fastjson转换对象
    public <T> List<T> toList(Class<T> clazz) {
        if (getFirstSourceText() == null) {
            return null;
        }
        return JSON.parseArray(getFirstSourceText(), clazz);
    }

    public PlainText jsonpathforList(String jsonPath) {
        return selectListForPlainText(new JsonPathSelector(jsonPath));
    }

    public PlainText jsonpath(String jsonPath){
        return selectForPlainText(new JsonPathSelector(jsonPath));
    }
}
