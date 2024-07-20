package com.jacky.marketservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacky.marketservice.Client.RequestClient;
import com.jacky.marketservice.dto.ItemPayload;
import com.jacky.marketservice.dto.Payload;
import com.jacky.marketservice.dto.response.ItemResponseDto;
import com.jacky.marketservice.model.Item;
import com.jacky.marketservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @Value( "${warframe.market.api.items}" )
    private String warframeMarketItemApi;

    @Transactional
    public void collectData(){
        RequestClient requestClient = new RequestClient();
        String response = requestClient.get(warframeMarketItemApi);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Payload<ItemPayload> payload =  objectMapper.readValue(response,  new TypeReference<Payload<ItemPayload>>() {});
            List<Item> items = payload.getPayload().getItems();
            int total = items.size();
            for (int counter = 0; counter < total; counter++){
                saveItem(items.get(counter), counter+1, total);
            }
            log.info("All items save successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ItemResponseDto> getItems(){
        List<Item> itemList= itemRepository.findAll();
        return itemList.stream().map(ItemResponseDto::new).collect(Collectors.toList());
    }

    public List<ItemResponseDto>  getItemByName(String name){
        List<Item> itemList= itemRepository.findByItemNameLike( String.format("%%%s%%", name));
        return itemList.stream().map(ItemResponseDto::new).collect(Collectors.toList());
    }

    public ItemResponseDto getItemById(String id){
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.map(ItemResponseDto::new).orElse(null);
    }

    private void saveItem(Item item, int counter, int total) {
        itemRepository.save(item);
        log.info("{}/{} - Item \"{}\" saved  ", logFormatProcess(counter), logFormatProcess(total), item.getItemName());
    }

    private String logFormatProcess(int value){
        return String.format("%5d", value);
    }
}
