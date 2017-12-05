package com.huaban.analysis.jieba.keyword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by zhangsihao on 2017/12/5.
 */
public class IdfDictionary {
    private static IdfDictionary instance;

    private double medianIdf = 0d;
    private Map<String, Double> idfFreq = new HashMap<>();

    public static IdfDictionary getInstance() {
        if (instance == null) {
            synchronized (IdfDictionary.class) {
                if (instance == null) {
                    instance = new IdfDictionary();
                }
            }
        }
        return instance;
    }

    private IdfDictionary() {
        System.out.println("Loading internal idf dictionary.");
        load(getClass().getResourceAsStream("/idf.txt"));
    }

    public void load(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length < 2 || parts[0].trim().length() == 0 || parts[1].trim().length() == 0) continue;
                try {
                    Double idf = Double.parseDouble(parts[1]);
                    idfFreq.put(parts[0], idf);
                } catch (Exception e) {
                    System.err.println(String.format("Illegal idf data. %s", line));
                }
            }
            List<Double> vals = new ArrayList<>(idfFreq.values());
            Collections.sort(vals);
            if(vals.size() % 2 == 0) medianIdf = (vals.get(vals.size() / 2) + vals.get(vals.size() / 2 + 1)) / 2;
            else medianIdf = vals.get(vals.size() / 2);
            System.out.println("Loaded stop word dictionary");
        } catch (Exception e) {
            System.err.println(String.format("Failed to load stop word dictionary. %s", e));
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return idfFreq.size();
    }

    public double getMedianIdf() {
        return medianIdf;
    }

    public double getIdf(String word) {
        Double idf = idfFreq.get(word);
        if (idf == null) {
            return medianIdf;
        } else {
            return idf;
        }
    }
}
