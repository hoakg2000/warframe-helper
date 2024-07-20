package com.jacky.marketservice.utils;

import org.springframework.beans.factory.annotation.Value;

public class ImageFilter {

    public static String getBigImageUrl(String url){
        return url.replace("/thumbs/", "/").replace(".128x128.",".");
    }
    public static String getSmallImageUrl(String url){
        return url;
    }
}
