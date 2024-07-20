package com.jacky.marketservice.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Component;

@Data
//@ConfigurationProperties(prefix = "warframe.market.api")
@ConfigurationProperties(prefix = "additional")
public class MyProperties {
    private String base;
    private String items;
    private String assets;

}
