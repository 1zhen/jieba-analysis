package com.huaban.analysis.jieba;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class JiebaTokenizer implements Tokenizer {
    private JiebaSegmenter.SegMode mode = JiebaSegmenter.SegMode.SEARCH;
    private JiebaSegmenter segmenter;

    public JiebaTokenizer() {
        this.segmenter = new JiebaSegmenter();
    }

    @Override
    public List<String> cut(String sentence) {
        List<SegToken> tokens = segmenter.process(sentence, mode);
        List<String> result = new ArrayList<>(tokens.size());
        for (SegToken token : tokens) {
            result.add(token.word);
        }
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private JiebaSegmenter.SegMode mode = JiebaSegmenter.SegMode.SEARCH;

        public Builder mode(JiebaSegmenter.SegMode mode) {
            this.mode = mode;
            return this;
        }

        public JiebaTokenizer build() {
            JiebaTokenizer result = new JiebaTokenizer();
            result.mode = this.mode;
            return result;
        }
    }
}
