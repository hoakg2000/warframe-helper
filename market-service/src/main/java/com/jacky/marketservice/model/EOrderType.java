package com.jacky.marketservice.model;

import lombok.Data;

public enum EOrderType {
    SELL("sell"),
    BUY("buy"),
    BUYANDSELL("buyandsave");

    private final String displayName;

    EOrderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
