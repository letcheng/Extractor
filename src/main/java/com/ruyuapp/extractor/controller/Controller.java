package com.ruyuapp.extractor.controller;

import com.ruyuapp.extractor.selector.CssSelector;
import com.ruyuapp.extractor.selector.ElementSelector;
import com.ruyuapp.extractor.selector.Selector;

import java.util.List;

/**
 * 抽取控制器接口
 */
public interface Controller {

    String get();
    String toString();
    boolean match();
    List<String> all();

    Controller regex(String regex);
    Controller regex(String regex, int group);
    Controller replace(String regex, String replacement);

    HtmlNode selectForHtmlNode(ElementSelector selector);
    PlainText selectForPlainText(Selector selector);
    HtmlNode selectListForHtmlNode(ElementSelector selector);
    PlainText selectListForPlainText(Selector selector);
    List<Controller> nodes();
}
