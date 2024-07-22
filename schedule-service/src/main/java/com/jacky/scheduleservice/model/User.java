package com.jacky.scheduleservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    String id;

    @JsonProperty("ingame_name")
    String ingameName;

    String status;

    @JsonProperty("last_seen")
    String lastSeen; //": "2024-07-19T00:35:35.132+00:00",

    public boolean isOnline(){
        return !status.equals("offline");
    }

    public boolean isIngame(){
        return status.equals("ingame");
    }

    public boolean recentOnline(){
        return false;
    }
}
