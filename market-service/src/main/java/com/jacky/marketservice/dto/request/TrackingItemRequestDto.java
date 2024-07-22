package com.jacky.marketservice.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jacky.marketservice.config.StatusEnumDeserializer;
import com.jacky.marketservice.model.EOrderType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingItemRequestDto {

    @NotNull
    @JsonDeserialize(using = StatusEnumDeserializer.class)
    private EOrderType orderType;

    @Min(1)
    @NotNull
    private Integer lowerBound;

    @Min(1)
    @NotNull
    private Integer upperBound;
}
