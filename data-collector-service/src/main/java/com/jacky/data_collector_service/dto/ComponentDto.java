package com.jacky.data_collector_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentDto extends ItemDto {
    private Long itemCount;
}
