package com.ruyuapp.extractor.controller;

import com.ruyuapp.extractor.selector.CssSelector;
import com.ruyuapp.extractor.selector.RegexSelector;
import com.ruyuapp.extractor.selector.Selector;
import org.apache.commons.collections.CollectionUtils;
import com.ruyuapp.extractor.mix.ReplaceSelector;
import com.ruyuapp.extractor.utils.SelectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器基类，包含一些基本的实现
 * @author letcheng letcheng@ruyuapp.com
 */
public abstract class AbstractController implements Controller {

    protected abstract List<String> getSourceTexts();

    public String getFirstSourceText() {
        if (getSourceTexts() != null && getSourceTexts().size() > 0) {
            return getSourceTexts().get(0);
        }
        return null;
    }

    public boolean match() {
        return getSourceTexts() != null && getSourceTexts().size() > 0;
    }
    public List<String> all() {
        return getSourceTexts();
    }
    public String toString() {
        return get();
    }
    public String get() {
        if (CollectionUtils.isNotEmpty(all())) {
            return all().get(0);
        } else {
            return null;
        }
    }

    public Controller regex(String regex) {
        return selectListForPlainText(SelectorFactory.regex(regex));
    }
    public Controller regex(String regex, int group) {
        RegexSelector regexSelector = SelectorFactory.regex(regex, group);
        return selectListForPlainText(regexSelector);
    }
    public Controller replace(String regex, String replacement) {
        return selectListForPlainText(new ReplaceSelector(regex, replacement));
    }

}
