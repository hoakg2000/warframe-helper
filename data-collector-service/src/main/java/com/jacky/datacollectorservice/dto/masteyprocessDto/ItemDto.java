package com.jacky.datacollectorservice.dto.masteyprocessDto;

import com.jacky.datacollectorservice.model.Recipe;
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
