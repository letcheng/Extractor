package com.ruyuapp.extractor.selector;

import com.ruyuapp.extractor.utils.ValueType;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * CSS抽取器
 * 参考开源项目 https://github.com/jhy/jsoup
 *
 * @author letcheng letcheng@ruyuapp.com
 */
public class CssSelector implements ElementSelector {


    private String selectorText;
    private ValueType valueType;

    public CssSelector(String selectorText) {
        this.selectorText = selectorText;
    }

    public CssSelector(String selectorText, ValueType valueType) {
        this.selectorText = selectorText;
        this.valueType = valueType;
    }


    @Override
    public String select(String text) {
       return this.getValue(this.select(Jsoup.parse(text)));
    }

    @Override
    public List<String> selectList(String text) {
        List<String> strings = new ArrayList<String>();
        List<Element> elements = selectList(Jsoup.parse(text));
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                String value = getValue(element);
                if (value != null) {
                    strings.add(value);
                }
            }
        }
        return strings;
    }

    @Override
    public Element select(Element element) {
        Elements elements = element.select(selectorText);
        if (CollectionUtils.isNotEmpty(elements)) {
            return elements.get(0);
        }
        return null;
    }

    @Override
    public List<Element> selectList(Element element) {
        return element.select(selectorText);
    }


    private String getValue(Element element) {
        switch (valueType) {
            case INNER_HTML:
                return element.html();
            case TEXT:
                StringBuilder sb = new StringBuilder();
                element.childNodes().stream().filter(node -> node instanceof TextNode).forEach(node -> {
                    TextNode textNode = (TextNode) node;
                    sb.append(textNode.text());
                });
                return sb.toString();
            case PURE_TEXT:
                element.text();
            case OUT_HTML:
            default:
                return element.outerHtml();
        }
    }

}
