package com.jacky.scheduleservice.utils;

public class Common {
    public static String generateWhisperMessage(String orderType, String username, String itemname, Integer price){
        return String.format("/w %s Hi! I want to %s \"%s\" for %d platinum (warframe.market) | jk-warframe-helper ",
                username, orderType.toUpperCase(), itemname, price);
    }
}
