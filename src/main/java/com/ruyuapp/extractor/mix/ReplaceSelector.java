package com.ruyuapp.extractor.mix;

import com.ruyuapp.extractor.selector.Selector;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author letcheng letcheng@ruyuapp.com
 */
public class ReplaceSelector implements Selector {

    private String replacement;
    private Pattern regex;

    public ReplaceSelector(String regexStr, String replacement) {
        this.replacement = replacement;
        try {
            regex = Pattern.compile(regexStr);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalide regexp", e);
        }
    }

    public String select(String text) {
        Matcher matcher = regex.matcher(text);
        return matcher.replaceAll(replacement);
    }

    public List<String> selectList(String text) {
        throw new UnsupportedOperationException();
    }
}
