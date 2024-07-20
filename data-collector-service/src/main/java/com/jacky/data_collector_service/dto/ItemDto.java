package com.jacky.data_collector_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jacky.data_collector_service.dto.ComponentDto;
import com.jacky.data_collector_service.model.Item;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto extends Item {

    @JsonProperty("components")
    private List<ComponentDto> componentDtos;

}
