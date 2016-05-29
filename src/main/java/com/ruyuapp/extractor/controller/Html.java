package com.ruyuapp.extractor.controller;

import com.ruyuapp.extractor.selector.ElementSelector;
import com.ruyuapp.extractor.selector.Selector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class Html extends HtmlNode {

    private Document document;

    private Html(String text) {
        super();
        try {
            Entities.EscapeMode.base.getMap().clear();
            Entities.EscapeMode.extended.getMap().clear();
            this.document = Jsoup.parse(text);
        } catch (Exception e) {
            this.document = null;
        }
    }

    public static Html create(String text) {
        return new Html(text);
    }

    @Override
    protected List<Element> getElements() {
        return Collections.<Element>singletonList(this.document);
    }

}
