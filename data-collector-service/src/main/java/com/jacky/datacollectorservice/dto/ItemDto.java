package com.jacky.datacollectorservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jacky.datacollectorservice.dto.ComponentDto;
import com.jacky.datacollectorservice.model.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto extends Item {

    @JsonProperty("components")
    @JsonManagedReference
    private List<ComponentDto> componentDtos;

}
