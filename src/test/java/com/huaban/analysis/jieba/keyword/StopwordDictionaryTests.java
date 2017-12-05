package com.huaban.analysis.jieba.keyword;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class StopwordDictionaryTests extends TestCase {
    @Test
    public void testLoad() {
        assertFalse(StopwordDictionary.getInstance().isEmpty());
        for (String word : StopwordDictionary.getInstance()) {
            assertFalse(StringUtils.isEmpty(word));
        }
    }

    @Test
    public void testContains() {
        assertTrue(StopwordDictionary.getInstance().contains("this"));
        assertFalse(StopwordDictionary.getInstance().contains("哈哈"));
    }
}
