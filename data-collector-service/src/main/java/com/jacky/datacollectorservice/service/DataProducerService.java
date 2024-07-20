package com.jacky.datacollectorservice.service;

import com.jacky.datacollectorservice.model.ECategory;
import com.jacky.datacollectorservice.model.Item;
import com.jacky.datacollectorservice.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DataProducerService {

    private final ItemRepository itemRepository;
    public List<Item> getItemByCategory(ECategory ECategory) {
        return itemRepository.findByCategory(ECategory.getDisplayName());
    }

    public List<Item> getWeapon() {
        return itemRepository.findByCategoryIn(Arrays.asList(ECategory.MELEE.getDisplayName(),
                ECategory.PRIMARY.getDisplayName(),
                ECategory.SECONDARY.getDisplayName(),
                ECategory.ARCHWING.getDisplayName(),
                ECategory.ARCH_MELEE.getDisplayName(),
                ECategory.ARCH_GUN.getDisplayName()));
    }

    public List<Item> searchItem(String name, int page, int size){
        return itemRepository.findByNameLike(String.format("%%%s%%", name), PageRequest.of(page, size));
    }
}
