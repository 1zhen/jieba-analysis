package com.huaban.analysis.jieba.keyword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class StopwordDictionary implements Collection<String> {
    private static StopwordDictionary instance;
    private Set<String> words = new HashSet<>();

    public static StopwordDictionary getInstance() {
        if (instance == null) {
            synchronized (StopwordDictionary.class) {
                if (instance == null) {
                    instance = new StopwordDictionary();
                }
            }
        }
        return instance;
    }

    private StopwordDictionary() {
        InputStream is = getClass().getResourceAsStream("/stopwords.txt");
        System.out.println("Loading internal stop word dictionary.");
        load(is);
    }

    public void load(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    words.add(line);
                }
            }
            System.out.println("Loaded stop word dictionary");
        } catch (Exception e) {
            System.err.println(String.format(Locale.getDefault(), "Failed to load stop word dictionary. %s", e));
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int size() {
        return words.size();
    }

    @Override
    public boolean isEmpty() {
        return words.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return words.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return words.iterator();
    }

    @Override
    public Object[] toArray() {
        return words.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return words.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return words.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return words.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return words.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return words.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return words.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return words.retainAll(c);
    }

    @Override
    public void clear() {
        words.clear();
    }
}
