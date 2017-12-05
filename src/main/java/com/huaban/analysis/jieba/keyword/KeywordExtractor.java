package com.huaban.analysis.jieba.keyword;

import java.util.List;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public interface KeywordExtractor {
    List<String> extract(String sentence);
}
