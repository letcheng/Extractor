package com.ruyuapp.extractor.mix;

import com.ruyuapp.extractor.selector.Selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 链式抽取，第一个抽取器的输出作为第二个抽取器的输入
 * @author letcheng letcheng@ruyuapp.com
 */
public class AndSelector implements Selector {

    private List<Selector> selectors = new ArrayList<Selector>();

    public AndSelector(Selector... selectors) {
        Collections.addAll(this.selectors, selectors);
    }

    public AndSelector(List<Selector> selectors) {
        this.selectors = selectors;
    }

    public String select(String text) {
        for (Selector selector : selectors) {
            if (text == null) {
                return null;
            }
            text = selector.select(text);
        }
        return text;
    }

    public List<String> selectList(String text) {
        List<String> results = new ArrayList<String>();
        boolean first = true;
        for (Selector selector : selectors) {
            if (first) {
                results = selector.selectList(text);
                first = false;
            } else {
                List<String> resultsTemp = new ArrayList<String>();
                for (String result : results) {
                    resultsTemp.addAll(selector.selectList(result));
                }
                results = resultsTemp;
                if (results.size() == 0) {
                    return results;
                }
            }
        }
        return results;
    }
}
