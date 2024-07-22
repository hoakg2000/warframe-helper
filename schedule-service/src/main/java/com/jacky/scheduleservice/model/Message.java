package com.jacky.scheduleservice.model;

import com.jacky.scheduleservice.utils.Common;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String orderId;
    private String urlName;
    private String username;
    private Integer price;
    private String orderType;
    private String whisperMessage;
    private Date createdTime;

    public Message(TrackingItem trackingItem, Order order){
        setOrderId(order.getId());
        setUrlName(trackingItem.getUrlName());
        setUsername(order.getUser().getIngameName());
        setPrice(order.getPlatinum());
        setOrderType(trackingItem.getOrderType());
        setWhisperMessage(Common.generateWhisperMessage(trackingItem.getOrderType(),
                order.getUser().getIngameName(),
                trackingItem.getName(),
                order.getPlatinum()));
        setCreatedTime(new Date());
    }

    public boolean equals(Message message) {
        return this.orderId.equals(message.getOrderId());
    }

    public boolean isExpired(Integer expiredTime) {
        long currentTimeInMillis = System.currentTimeMillis();
        long messageTimeInMillis = createdTime.getTime();
        return (currentTimeInMillis - messageTimeInMillis) > expiredTime;
    }
}
