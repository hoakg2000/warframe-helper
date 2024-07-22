package com.jacky.scheduleservice.utils;

public class Common {
    public static String generateWhisperMessage(String orderType, String username, String itemname, Integer price){
        return String.format("/w {} Hi! I want to {} \"{}\" for {} platinum (warframe.market) | jk-warframe-helper ",
                username,orderType.toUpperCase(), itemname, price);
    }
}
