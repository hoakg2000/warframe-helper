package com.jacky.marketservice.controller;

import com.jacky.marketservice.dto.response.ItemResponseDto;
import com.jacky.marketservice.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/collectdata")
    public void collectData(){
        itemService.collectData();
    }

    @GetMapping
    public List<ItemResponseDto> getAll(){
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public ItemResponseDto getById(@PathVariable String id){
        return itemService.getItemById(id);
    }

    @GetMapping("/name/{name}")
    public List<ItemResponseDto> getByName(@PathVariable String name){
        return itemService.getItemByName(name);
    }

}
