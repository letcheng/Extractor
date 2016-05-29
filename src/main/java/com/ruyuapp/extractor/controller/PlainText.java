package com.ruyuapp.extractor.controller;

import com.ruyuapp.extractor.selector.ElementSelector;
import com.ruyuapp.extractor.selector.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Text 控制器
 * @author letcheng letcheng@ruyuapp.com
 */
public class PlainText extends AbstractController {

    private List<String> sourceText;

    public PlainText(String text) {
        this.sourceText = new ArrayList<String>();
        sourceText.add(text);
    }

    public PlainText(List<String> sourceText){
        this.sourceText = sourceText;
    }

    public static PlainText create(String text) {
        return new PlainText(text);
    }

    @Override
    public HtmlNode selectForHtmlNode(ElementSelector selector) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PlainText selectListForPlainText(Selector selector) {
        List<String> results = new ArrayList<String>();
        for (String string : getSourceTexts()) {
            List<String> result = selector.selectList(string);
            results.addAll(result);
        }
        return new PlainText(results);
    }

    @Override
    public HtmlNode selectListForHtmlNode(ElementSelector selector) {
        throw new UnsupportedOperationException();
    }
    @Override
    public PlainText selectForPlainText(Selector selector) {
        List<String> results = new ArrayList<String>();
        for (String string : getSourceTexts()) {
            String result = selector.select(string);
            if (result != null) {

                results.add(result);
            }
        }
        return new PlainText(results);
    }

    public List<Controller> nodes() {
        List<Controller> nodes = new ArrayList<Controller>(getSourceTexts().size());
        sourceText.stream().forEach(s -> {
            nodes.add(PlainText.create(s));
        });
        return nodes;
    }

    @Override
    protected List<String> getSourceTexts() {
        List<String> tmps = new ArrayList<String>();
        sourceText.stream().forEach(tmps::add);
        return tmps;
    }
}
