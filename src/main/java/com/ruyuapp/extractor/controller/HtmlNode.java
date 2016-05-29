package com.ruyuapp.extractor.controller;

import com.ruyuapp.extractor.selector.*;
import com.ruyuapp.extractor.utils.ValueType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.ruyuapp.extractor.utils.SelectorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * Html 抽取器
 */
public class HtmlNode extends AbstractController {

    private final List<Element> elements;

    public HtmlNode(List<Element> elements) {
        this.elements = elements;
    }

    public HtmlNode() {
        elements = null;
    }

    protected List<Element> getElements() {
        return elements;
    }

    @Override
    public HtmlNode selectForHtmlNode(ElementSelector elementSelector) {
        ListIterator<Element> elementIterator = getElements().listIterator();
        List<Element> resultElements = new ArrayList<Element>();
        while (elementIterator.hasNext()) {
            Element element = checkElementAndConvert(elementIterator);
            Element element1 = elementSelector.select(element);
            resultElements.add(element1);
        }
        return new HtmlNode(resultElements);
    }

    @Override
    public PlainText selectForPlainText(Selector selector) {
        if(selector instanceof ElementSelector) {
            ListIterator<Element> elementIterator = getElements().listIterator();
            List<String> resultString = new ArrayList<String>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                if(element==null)return new PlainText("");
                String str = selector.select(element.html());
                resultString.add(str);
            }
            return new PlainText(resultString);
        }
        return new PlainText("");
    }

    @Override
    public HtmlNode selectListForHtmlNode(ElementSelector elementSelector) {
        ListIterator<Element> elementIterator = getElements().listIterator();
        List<Element> resultElements = new ArrayList<Element>();
        while (elementIterator.hasNext()) {
            Element element = checkElementAndConvert(elementIterator);
            List<Element> selectElements = elementSelector.selectList(element);
            resultElements.addAll(selectElements);
        }
        return new HtmlNode(resultElements);
    }

    @Override
    public PlainText selectListForPlainText(Selector selector) {
        if(selector instanceof ElementSelector) {
            ListIterator<Element> elementIterator = getElements().listIterator();
            List<String> resultString = new ArrayList<String>();
            while (elementIterator.hasNext()) {
                Element element = checkElementAndConvert(elementIterator);
                if(element==null)return null;
                List<String> str = selector.selectList(element.html());
                resultString.addAll(str);
            }
            return new PlainText(resultString);
        }
        return null;
    }


    private Element checkElementAndConvert(ListIterator<Element> elementIterator) {
        Element element = elementIterator.next();
        if(element==null)return null;
        if (!(element instanceof Document)) {
            Document root = new Document(element.ownerDocument().baseUri());
            Element clone = element.clone();
            root.appendChild(clone);
            elementIterator.set(root);
            return root;
        }
        return element;
    }

    public Controller links() {
        return xpath("//a/@href");
    }

    public Controller xpath(String xpath) {
        return selectForHtmlNode(SelectorFactory.xpath(xpath));
    }

    public Controller $(String selector) {
        return selectForHtmlNode(SelectorFactory.$(selector));
    }

    public Controller $(String selector, ValueType valueType) {
        return selectForHtmlNode(SelectorFactory.$(selector, valueType));
    }

    public List<Controller> nodes() {
        List<Controller> controllers = new ArrayList<Controller>();
        for (Element element : getElements()) {
            List<Element> childElements = new ArrayList<Element>(1);
            childElements.add(element);
            controllers.add(new HtmlNode(childElements));
        }
        return controllers;
    }

    @Override
    protected List<String> getSourceTexts() {
        List<String> sourceTexts = new ArrayList<String>(getElements().size());
        sourceTexts.addAll(getElements().stream().map(Element::toString).collect(Collectors.toList()));
        return sourceTexts;
    }
}
