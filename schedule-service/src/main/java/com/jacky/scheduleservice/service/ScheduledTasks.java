package com.jacky.scheduleservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacky.scheduleservice.Client.RequestClient;
import com.jacky.scheduleservice.model.*;
import com.jacky.scheduleservice.utils.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {

    private KafkaService kafkaService;
    private final WebClient.Builder webClientBuilder;

    @Value( "${myservice.marketservice.api.trackingitem}" )
    private String marketServiceTrackingApi;

    @Value( "${warframe.market.api.items}" )
    private String warframeMarketItemApi;

    private TrackingItem[] previousTrackingItemList;

    @Value( "${message.keeping.expiretime}" )
    private Integer producedMessageExpiretime;

    private List<Message> producedMessage;

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void performTask() throws JsonProcessingException {
        log.info("Starting execute schedule task - {}", new Date());

        //get tracking item list infomation
        List<TrackingItem> trackingItemList = getTrackingList();
        if (trackingItemList == null || trackingItemList.isEmpty()){
            return;
        }

        for (TrackingItem trackingItem: trackingItemList){
            log.info("Item <{}>", trackingItem.getName());
            List<Order> orderList = getMarketNewOrder(trackingItem);
            if (orderList.isEmpty()){
                continue;
            }
            //remove duplicate
            orderList = removeAlreadyDuplicateMessage(orderList);

            for (Order order: orderList){
                log.info("{} name {} with price {}, copy to message: /w {} Hi! I want to {} \"{}\" for {} platinum (warframe.market) | jk-warframe-helper ",
                        order.getOrderType().toUpperCase(),
                        order.getUser().getIngameName(),
                        order.getPlatinum(),
                        order.getUser().getIngameName(),
                        trackingItem.getOrderType().toLowerCase(),
                        trackingItem.getName(),
                        order.getPlatinum());
                produceMessage(trackingItem, order);
            }
        }

        log.info("Finished executed schedule task - {}", new Date());
    }

    private List<TrackingItem> getTrackingList(){
        log.info("Get tracking items");
        TrackingItem[] trackingItemList = webClientBuilder.build().get()
                .uri(marketServiceTrackingApi)
                .retrieve()
                .bodyToMono(TrackingItem[].class)
                .block();

        if (trackingItemList != null){
            previousTrackingItemList = trackingItemList;
        }else{
            trackingItemList = previousTrackingItemList;
        }
        return Arrays.stream(trackingItemList).toList();
    }

    private List<Order> getMarketNewOrder(TrackingItem trackingItem) throws JsonProcessingException {
        log.info("Get market new orders");
        RequestClient requestClient = new RequestClient();
        String requestApi = String.format("%s/%s/%s", warframeMarketItemApi, trackingItem.getUrlName(), "orders?include=item");

        String response = requestClient.get(requestApi);
        JsonMapper jsonMapper = new JsonMapper();
        Payload payload = (Payload) jsonMapper.convertStringToClass(response, Payload.class);

        List<Order> orders = payload.getPayload().getOrders();

        return orders.stream().filter(order -> order.isAvailable(trackingItem)).collect(Collectors.toList());
    }

    private List<Order> removeAlreadyDuplicateMessage(List<Order> orders){
        producedMessage = producedMessage.stream().filter(message -> message.isExpired(producedMessageExpiretime)).toList();
        return orders.stream().filter(order ->
                producedMessage.stream().filter(producedMessage ->
                        producedMessage.getOrderId().equals(order.getId())
                ).toList().isEmpty()
        ).toList();
    }

    private Boolean produceMessage(TrackingItem trackingItem, Order order){
        log.info("Sending message for <{}>", trackingItem.getName());
        Message message = new Message(trackingItem, order);
        producedMessage.add(message);
        return null;
    }
}
