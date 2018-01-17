package com.huaban.analysis.jieba.keyword;

import com.huaban.analysis.jieba.JiebaTokenizer;
import com.huaban.analysis.jieba.Tokenizer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class TfIdfKeywordExtractor implements KeywordExtractor {
    private Tokenizer tokenizer;
    private IdfDictionary idfDictionary = IdfDictionary.getInstance();
    private StopwordDictionary stopwordDictionary = StopwordDictionary.getInstance();

    private TfIdfKeywordExtractor() {

    }

    @Override
    public List<String> extract(String sentence) {
        return extract(sentence, null, null);
    }

    @Override
    public List<String> extract(String sentence, Integer topK) {
        return extract(sentence, topK, null);
    }

    @Override
    public List<String> extract(String sentence, Double minWeight) {
        return extract(sentence, null, minWeight);
    }

    @Override
    public List<String> extract(String sentence, Integer topK, final Double minWeight) {
        Map<String, Double> freq = new HashMap<>();
        List<String> words = tokenizer.cut(sentence);
        for (String word : words) {
            if (word.trim().length() < 2 || stopwordDictionary.contains(word.toLowerCase(Locale.getDefault()))) {
                continue;
            }
            if (freq.containsKey(word)) {
                freq.put(word, freq.get(word) + 1d);
            } else {
                freq.put(word, 1d);
            }
        }
        double total = 0d;
        for (Double val : freq.values()) {
            total += val;
        }
        for (String word : freq.keySet()) {
            freq.put(word, freq.get(word) * idfDictionary.getIdf(word) / total);
        }
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(freq.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        if (minWeight != null) {
            entryList = entryList.stream().filter(entry -> entry.getValue() >= minWeight).collect(Collectors.toList());
        }
        if (topK != null) {
            if (entryList.size() >= topK) {
                entryList = entryList.subList(0, topK);
            }
        }
        List<String> keywords = new ArrayList<>();
        for (Map.Entry<String, Double> entry : entryList) {
            keywords.add(entry.getKey());
        }
        return keywords;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static Tokenizer DEFAULT_TOKENIZER = JiebaTokenizer.builder().build();

        private Tokenizer tokenizer = DEFAULT_TOKENIZER;

        public Builder tokenizer(Tokenizer tokenizer) {
            this.tokenizer = tokenizer;
            return this;
        }

        public TfIdfKeywordExtractor build() {
            TfIdfKeywordExtractor result = new TfIdfKeywordExtractor();
            result.tokenizer = this.tokenizer;
            return result;
        }
    }
}
