package com.jacky.warframe.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {
    private String orderId;
    private String urlName;
    private String username;
    private Integer price;
    private String orderType;
    private String whisperMessage;
    private Date createdTime;
}
