package com.jacky.data_collector_service.dto.Request;

import com.jacky.data_collector_service.model.EMasteryStatus;
import lombok.Data;

@Data
public class UpdateMasteryProcess {
    private EMasteryStatus eMasteryStatus = EMasteryStatus.INCOMPLETE;
}
