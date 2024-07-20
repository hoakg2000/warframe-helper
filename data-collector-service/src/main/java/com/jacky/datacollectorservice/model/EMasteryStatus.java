package com.jacky.datacollectorservice.model;

public enum EMasteryStatus {

    COMPLETE("complete"),
    ONGOING("ongoing"),
    INCOMPLETE("incomplete");

    private final String displayName;

    EMasteryStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
