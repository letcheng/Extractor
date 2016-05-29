package com.ruyuapp.extractor.mix;

import com.ruyuapp.extractor.selector.Selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author letcheng letcheng@ruyuapp.com
 */
public class OrSelector implements Selector {

    private List<Selector> selectors = new ArrayList<Selector>();

    public OrSelector(Selector... selectors) {
        Collections.addAll(this.selectors, selectors);
    }

    public OrSelector(List<Selector> selectors) {
        this.selectors = selectors;
    }

    public String select(String text) {
        for (Selector selector : selectors) {
            String result = selector.select(text);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public List<String> selectList(String text) {
        List<String> results = new ArrayList<String>();
        for (Selector selector : selectors) {
            List<String> strings = selector.selectList(text);
            results.addAll(strings);
        }
        return results;
    }
}
