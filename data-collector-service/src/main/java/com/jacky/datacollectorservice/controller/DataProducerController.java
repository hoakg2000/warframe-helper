package com.jacky.datacollectorservice.controller;

import com.jacky.datacollectorservice.model.ECategory;
import com.jacky.datacollectorservice.model.Item;
import com.jacky.datacollectorservice.service.DataProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataProducerController {

    private final DataProducerService dataProducerService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/category/{category}")
    public List<Item> getItemByCategory(@PathVariable("category") ECategory ECategory) {
        return dataProducerService.getItemByCategory(ECategory);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/weapon")
    public List<Item> getItemByCategory() {
        return dataProducerService.getWeapon();
    }

    @GetMapping("/search")
    public List<Item> searchItem(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String name
    ){
            return dataProducerService.searchItem(name, page, size);
    }
}
