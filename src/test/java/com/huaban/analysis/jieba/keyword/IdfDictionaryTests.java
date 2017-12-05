package com.huaban.analysis.jieba.keyword;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class IdfDictionaryTests extends TestCase {
    @Test
    public void testLoad() {
        IdfDictionary dictionary = IdfDictionary.getInstance();
        assertTrue(dictionary.size() > 0);
        assertEquals(dictionary.getMedianIdf(), dictionary.getIdf("haha"));
        assertEquals(11.7034530746d, dictionary.getIdf("笑哈哈"));
    }
}
