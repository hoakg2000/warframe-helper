package com.jacky.data_collector_service.dto.Request;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
@Data
public class CreateMastery {
    @NotNull
    String accountId;
}
