package com.jacky.data_collector_service.dto.masteyprocessDto;

import com.jacky.data_collector_service.model.EMasteryStatus;
import com.jacky.data_collector_service.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasteryProcessDto {
    private Long id;
    private String accountId;
    private ItemDto item;
    private EMasteryStatus eMasteryStatus;
    private Integer priorityLevel;
}
