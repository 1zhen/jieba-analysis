package com.huaban.analysis.jieba.keyword;

import java.util.List;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public interface KeywordExtractor {
    List<String> extract(String sentence);
    List<String> extract(String sentence, Integer topK);
    List<String> extract(String sentence, Double minWeight);
    List<String> extract(String sentence, Integer topK, Double minWeight);
}
