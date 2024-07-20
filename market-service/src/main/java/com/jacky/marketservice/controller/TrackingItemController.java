package com.jacky.marketservice.controller;

import com.jacky.marketservice.dto.request.TrackingItemRequestDto;
import com.jacky.marketservice.model.TrackingItem;
import com.jacky.marketservice.service.TrackingItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/track")
@AllArgsConstructor
public class TrackingItemController {

    private final TrackingItemService trackingItemService;

    @GetMapping
    public List<TrackingItem> getAll(){
        return trackingItemService.getAll();
    }

    @PostMapping("/{item_id}")
    public TrackingItem create(@PathVariable("item_id") String itemId,
                               @RequestBody @Valid TrackingItemRequestDto trackingItemRequestDto){
        return trackingItemService.create(itemId, trackingItemRequestDto);
    }

    @PutMapping("/{item_id}")
    public TrackingItem update(@PathVariable("item_id") String itemId,
                               @RequestBody @Valid TrackingItemRequestDto trackingItemRequestDto){
        return trackingItemService.update(itemId, trackingItemRequestDto);
    }

    @DeleteMapping("/{item_id}")
    public void delete(@PathVariable("item_id") String itemId){
        trackingItemService.delete(itemId);
    }

}
