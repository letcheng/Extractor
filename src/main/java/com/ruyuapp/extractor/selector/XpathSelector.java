package com.ruyuapp.extractor.selector;

import com.jcabi.xml.XMLDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 *  Xpath抽取器
 *  参考开源项目 https://github.com/jcabi/jcabi-xml
 *  @author letcheng letcheng@ruyuapp.com
 */
public class XpathSelector implements ElementSelector {

    private String xpath;

    public XpathSelector(String xpath){
        this.xpath = xpath;
    }

    @Override
    public String select(String text) {
        return new XMLDocument(text).xpath(xpath).get(0);
    }

    @Override
    public List<String> selectList(String text) {
        return new XMLDocument(text).xpath(xpath);
    }

    @Override
    public Element select(Element element) {
        return Jsoup.parse(this.select(element.html()));
    }

    @Override
    public List<Element> selectList(Element element) {
        return (Jsoup.parse(this.select(element.html()))).getAllElements();
    }
}
