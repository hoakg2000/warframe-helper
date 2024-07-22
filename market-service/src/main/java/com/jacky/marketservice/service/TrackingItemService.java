package com.jacky.marketservice.service;

import com.jacky.marketservice.dto.request.TrackingItemRequestDto;
import com.jacky.marketservice.dto.response.TrackingItemResponseDto;
import com.jacky.marketservice.model.Item;
import com.jacky.marketservice.model.TrackingItem;
import com.jacky.marketservice.repository.ItemRepository;
import com.jacky.marketservice.repository.TrackingItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingItemService {

    private final TrackingItemRepository trackingItemRepository;

    private final ItemRepository itemRepository;

    @Value( "${warframe.market.tracking.limit}" )
    private Integer trackingLimit;
    public List<TrackingItemResponseDto> getAll() {
        return trackingItemRepository.findAll().stream()
                .map(TrackingItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public TrackingItem create(String itemId, TrackingItemRequestDto trackingItemRequestDto) {
        List<TrackingItem> trackingItems = trackingItemRepository.findAll();
        if (trackingItems.size() >= trackingLimit){
            throw new RuntimeException(String.format("Cannot track over %d", trackingLimit));
        }

        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isEmpty()){
            throw new RuntimeException(String.format("Cannot find item with id %s", itemId));
        }

        Optional<TrackingItem> optionalTrackingItem = trackingItemRepository.findById(itemId);
        if (optionalTrackingItem.isPresent()){
            throw new RuntimeException(String.format("Item \"%s\" has already been track before", itemId));
        }

        TrackingItem trackingItem = TrackingItem.builder()
                .item(item.get())
                .lowerBound(trackingItemRequestDto.getLowerBound())
                .upperBound(trackingItemRequestDto.getUpperBound())
                .orderType(trackingItemRequestDto.getOrderType().getDisplayName())
                .build();

        trackingItemRepository.save(trackingItem);
        log.info("Create new tracking for item \"{}\"", trackingItem.getItem().getItemName());

        return trackingItem;
    }

    public TrackingItem update(String itemId, TrackingItemRequestDto trackingItemRequestDto) {
        Optional<TrackingItem> optionalTrackingItem = trackingItemRepository.findById(itemId);
        if (optionalTrackingItem.isEmpty()){
            throw new RuntimeException(String.format("Item \"%s\" has already been track before", itemId));
        }

        TrackingItem trackingItem = optionalTrackingItem.get();
        trackingItem.setLowerBound(trackingItemRequestDto.getLowerBound())
                .setUpperBound(trackingItemRequestDto.getUpperBound())
                .setOrderType(trackingItemRequestDto.getOrderType().getDisplayName());

        trackingItemRepository.save(trackingItem);
        return trackingItem;
    }

    public void delete(String itemId) {
        trackingItemRepository.deleteById(itemId);
    }
}
