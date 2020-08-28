package com.hjrpc.serverpush.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessageUtils {
    public static Map<String, Integer> getRandomMap() {
        Random random = new Random();
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("apple", 10 + random.nextInt(10));
        resultMap.put("banana", 10 + random.nextInt(10));
        resultMap.put("orange", 10 + random.nextInt(10));
        resultMap.put("peach", 10 + random.nextInt(10));
        return resultMap;
    }
}
