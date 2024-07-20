package com.jacky.datacollectorservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacky.datacollectorservice.dto.ComponentDto;
import com.jacky.datacollectorservice.dto.ItemDto;
import com.jacky.datacollectorservice.model.Item;
import com.jacky.datacollectorservice.model.Recipe;
import com.jacky.datacollectorservice.repository.ItemRepository;
import com.jacky.datacollectorservice.repository.RecipeRepository;
import com.jacky.datacollectorservice.util.DataCollectorFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class DataCollectorService {

    private final ItemRepository itemRepository;
    private final RecipeRepository recipeRepository;

    private final String warframeApiItem = "https://api.warframestat.us/items/";

    @Transactional
    public void collectData(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(warframeApiItem))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            List<ItemDto> items = objectMapper.readValue(response.body(),  new TypeReference<List<ItemDto>>() {});
            int total = items.size();
            for (int counter = 0; counter < total; counter++){
                saveItem(items.get(counter), counter+1, total);
            }
            log.info("All items save successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveItem(ItemDto itemDto, int counter, int total){
        if (!DataCollectorFilter.category.contains(itemDto.getCategory())){
            return;
        }
        Item newItem = new Item(itemDto);
        List<Recipe> recipeList = new ArrayList<>();
        if (itemDto.getDrops() != null && itemDto.getDrops().size() > 0){
            itemDto.getDrops().forEach(drop -> drop.setUniqueName(itemDto.getUniqueName()));
        }
        //check if item has an crafting recipe
        if (itemDto.getComponentDtos() != null && itemDto.getComponentDtos().size() > 0){

            //make sure all recipe's component already save in db
            for (ComponentDto componentDTO : itemDto.getComponentDtos()) {
                if (itemRepository.findById(componentDTO.getUniqueName()).isEmpty()){
                    componentDTO.setIsComponent(true);
                    itemRepository.save(new Item(componentDTO));
                    log.info("Item component \"{}\" saved", componentDTO.getName());
                }

                Item component = itemRepository.findById(componentDTO.getUniqueName()).get();
                recipeList.add(Recipe.builder()
                        .item(newItem)
                        .itemComponent(component)
                        .itemCount(componentDTO.getItemCount())
                        .build());
            }
        }
//        newItem.setComponents(recipeList);
        itemRepository.save(newItem);
        recipeList.forEach(recipeRepository::save);
        log.info("{}/{} - Item \"{}\" saved  ", logFormatProcess(counter), logFormatProcess(total), newItem.getName());
    }

    private String logFormatProcess(int value){
       return String.format("%5d", value);
    }
}
