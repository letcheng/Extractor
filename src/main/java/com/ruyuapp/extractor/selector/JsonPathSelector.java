package com.ruyuapp.extractor.selector;

import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * JsonPath抽取器
 * 参考：http://goessner.net/articles/JsonPath/
 * @author letcheng letcheng@ruyuapp.com
 */
public class JsonPathSelector implements Selector {

    private JsonPath jsonPath;

    public JsonPathSelector(String jsonPathStr) {
        this.jsonPath = JsonPath.compile(jsonPathStr);
    }

    public String select(String text) {
        //返回的数据 可能有3种情况： 空、字符串和List集合
        Object object = jsonPath.read(text);
        if (object == null) {
            return null;
        }
        if (object instanceof List) {
            List list = (List) object;
            if (list.size() > 0) {
                return list.iterator().next().toString(); //返回第一个数据
            }
        }
        return object.toString();
    }

    public List<String> selectList(String text) {
        List<String> list = new ArrayList<String>();
        Object object = jsonPath.read(text);
        if (object == null) {
            return list;
        }
        if (object instanceof List) {
            list.addAll(((List<?>) object).stream().map(String::valueOf).collect(Collectors.toList()));
        } else {
            list.add(String.valueOf(object));
        }
        return list;
    }
}
