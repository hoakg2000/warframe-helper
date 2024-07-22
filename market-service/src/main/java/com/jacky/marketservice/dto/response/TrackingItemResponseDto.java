package com.jacky.marketservice.dto.response;

import com.jacky.marketservice.model.TrackingItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingItemResponseDto {
    private String id;
    private String name;
    private String urlName;
    private String orderType;
    private Integer lowerBound;
    private Integer upperBound;

    public TrackingItemResponseDto(TrackingItem trackingItem){
        setId(trackingItem.getItemId());
        setName(trackingItem.getItem().getItemName());
        setUrlName(trackingItem.getItem().getUrlName());
        setOrderType(trackingItem.getOrderType());
        setLowerBound(trackingItem.getLowerBound());
        setUpperBound(trackingItem.getUpperBound());
    }
}
