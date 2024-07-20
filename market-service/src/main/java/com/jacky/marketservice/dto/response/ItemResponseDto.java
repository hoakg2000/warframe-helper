package com.jacky.marketservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jacky.marketservice.model.Item;
import com.jacky.marketservice.utils.ImageFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResponseDto {
    private String itemId;
    private String itemName;
    private String urlName;
    private Boolean vaulted;
    private String bigImage;
    private String smallImage;
    public ItemResponseDto(Item item){
        setItemId(item.getItemId());
        setItemName(item.getItemName());
        setUrlName(item.getUrlName());
        setVaulted(item.getVaulted());
        setBigImage(ImageFilter.getBigImageUrl(item.getThumb()));
        setSmallImage(ImageFilter.getSmallImageUrl(item.getThumb()));
    }
}
