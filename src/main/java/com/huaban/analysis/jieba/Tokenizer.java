package com.huaban.analysis.jieba;

import java.util.List;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public interface Tokenizer {
    List<String> cut(String sentence);
}
