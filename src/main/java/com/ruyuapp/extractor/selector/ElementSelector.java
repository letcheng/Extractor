package com.ruyuapp.extractor.selector;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * HTML ELEMENT抽取器
 * @author letcheng letcheng@ruyuapp.com
 */
public interface ElementSelector extends Selector {
    Element select(Element element);
    List<Element> selectList(Element element);
}
