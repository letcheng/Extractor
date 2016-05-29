package com.ruyuapp.extractor.utils;

import com.ruyuapp.extractor.selector.*;
import com.ruyuapp.extractor.mix.AndSelector;
import com.ruyuapp.extractor.mix.OrSelector;

/**
 * 抽取器的工厂类
 * @author letcheng letcheng@ruyuapp.com
 */
public abstract class SelectorFactory {

    public static RegexSelector regex(String expr) {
        return new RegexSelector(expr);
    }

    public static JsonPathSelector jsonpath(String expr) {
        return new JsonPathSelector(expr);
    }

    public static RegexSelector regex(String expr, int group) {
        return new RegexSelector(expr,group);
    }

    public static CssSelector $(String expr) {
        return new CssSelector(expr);
    }

    public static CssSelector $(String expr, ValueType valueType) {
        return new CssSelector(expr, valueType);
    }

    public static XpathSelector xpath(String expr) {
        return new XpathSelector(expr);
    }

    public static AndSelector and(Selector... selectors) {
        return new AndSelector(selectors);
    }

    public static OrSelector or(Selector... selectors) {
        return new OrSelector(selectors);
    }

}