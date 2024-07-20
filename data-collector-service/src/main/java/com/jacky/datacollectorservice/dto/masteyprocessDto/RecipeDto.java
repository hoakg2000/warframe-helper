package com.jacky.datacollectorservice.dto.masteyprocessDto;

import com.jacky.datacollectorservice.model.Item;
import com.jacky.datacollectorservice.model.Recipe;
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
