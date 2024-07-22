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
public class Order {
    private String id;

    private User user;

    @JsonProperty("last_update")
    private String lastUpdate;

    @JsonProperty("order_type")
    private String orderType;

    private Integer platinum;

    private Integer quantity;

    public boolean isAvailable(TrackingItem trackingItem){
        try {
            return isIngame()
                    && isInTrackingZone(trackingItem.getLowerBound(), trackingItem.getUpperBound())
                    && mathchingOrderType(trackingItem.getOrderType());
        }catch (Exception e){
            return false;
        }
    }

    public boolean isOnline(){
        return user.isOnline();
    }


    public boolean isIngame(){
        return user.isIngame();
    }

    public boolean isInTrackingZone(Integer lower, Integer upper){
        return platinum >= lower && platinum <= upper;
    }

    public boolean mathchingOrderType(String trackingOrder){
        String marketOrderType = this.orderType.toUpperCase();
        String trackingOrderType = trackingOrder.toUpperCase();
        if (marketOrderType.equals(trackingOrderType)){
            return true;
        }
        return trackingOrderType.equals("BUYANDSELL");
    }
}
