package com.jacky.data_collector_service.dto.masteyprocessDto;

import com.jacky.data_collector_service.model.Item;
import com.jacky.data_collector_service.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private String uniqueName;
    private Long itemCount;

    public RecipeDto(Recipe recipe){
        this.uniqueName = recipe.getItemComponent().getUniqueName();
        this.itemCount = recipe.getItemCount();
    }
}
