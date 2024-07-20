package com.jacky.datacollectorservice.dto.Request;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
@Data
public class CreateMastery {
    @NotNull
    String accountId;
}
