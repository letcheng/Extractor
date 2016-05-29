package com.ruyuapp.extractor.selector;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import com.ruyuapp.extractor.utils.RegexResult;

/**
 * Regexp抽取器
 * @author letcheng letcheng@ruyuapp.com
 */
public class RegexSelector implements Selector {

    private String regexStr;

    private Pattern regex;

    private int group = 1;

    public RegexSelector(String regexStr, int group) {
        if (StringUtils.isBlank(regexStr)) {
            throw new IllegalArgumentException("regex must not be empty");
        }
        // Check bracket for regex group. Add default group 1 if there is no group.
        // Only check if there exists the valid left parenthesis, leave regexp validation for Pattern.
        if (StringUtils.countMatches(regexStr, "(") - StringUtils.countMatches(regexStr, "\\(") ==
                StringUtils.countMatches(regexStr, "(?:") - StringUtils.countMatches(regexStr, "\\(?:")) {
            regexStr = "(" + regexStr + ")";
        }
        this.regexStr = regexStr;
        try {
            regex = Pattern.compile(regexStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalid regex", e);
        }
        this.group = group;
    }

    public RegexSelector(String regexStr) {
        this(regexStr, 1);
    }

    public String select(String text) {
        return selectGroup(text).get(group);
    }

    public List<String> selectList(String text) {
        List<String> strings = new ArrayList<String>();
        List<RegexResult> results = selectGroupList(text);
        strings.addAll(results.stream().map(result -> result.get(group)).collect(Collectors.toList()));
        return strings;
    }

    public RegexResult selectGroup(String text) {
        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            String[] groups = new String[matcher.groupCount() + 1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            return new RegexResult(groups);
        }
        return RegexResult.EMPTY_RESULT;
    }

    public List<RegexResult> selectGroupList(String text) {
        Matcher matcher = regex.matcher(text);
        List<RegexResult> resultList = new ArrayList<RegexResult>();
        while (matcher.find()) {
            String[] groups = new String[matcher.groupCount() + 1];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = matcher.group(i);
            }
            resultList.add(new RegexResult(groups));
        }
        return resultList;
    }

    @Override
    public String toString() {
        return regexStr;
    }

}
