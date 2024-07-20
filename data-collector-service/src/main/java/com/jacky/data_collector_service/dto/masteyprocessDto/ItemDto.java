package com.jacky.data_collector_service.dto.masteyprocessDto;

import com.jacky.data_collector_service.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String uniqueName;
    private String name;
    private String category;
    private String imageName;
    private Integer masteryReq;
    private List<RecipeDto> components;
}
