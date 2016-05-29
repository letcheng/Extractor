package com.ruyuapp.extractor.selector;

import java.util.List;


public interface Selector {
    String select(String text);
    List<String> selectList(String text);
}
