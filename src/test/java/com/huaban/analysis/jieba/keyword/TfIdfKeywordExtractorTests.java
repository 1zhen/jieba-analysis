package com.huaban.analysis.jieba.keyword;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaTokenizer;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class TfIdfKeywordExtractorTests extends TestCase {
    private static final List<String> SENTENCES = new ArrayList<String>() {{
        add("奶奶棋牌室忙着打麻将 2岁孙女误开三轮冲入池塘溺亡");
        add("关晓彤演技炸裂转型成功 《极光之恋》收视第一成黑马");
        add("物理试卷现世纪难题：老师、鹿晗、马云、库里谁帅？你选谁");
        add("【英雄周报】王者荣耀新版干将莫邪视频教学");
        add("《心理罪之城市之光》正义绝不缺席预告 邓超拍肉搏戏累到虚脱 The Liquidator");
        add("鹿晗、吴亦凡、杨幂、Baby同框排排坐 原来他们听课是这个样的");
    }};

    @Test
    public void testExtract() {
        KeywordExtractor extractor = TfIdfKeywordExtractor.builder().build();
        for (String sentence : SENTENCES) {
            List<String> keywords = extractor.extract(sentence);
            System.out.println(String.format("%s : %s", sentence, StringUtils.join(keywords, " ")));
        }
    }
}
