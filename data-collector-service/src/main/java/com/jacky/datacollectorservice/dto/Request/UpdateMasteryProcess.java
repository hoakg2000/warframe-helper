package com.jacky.datacollectorservice.dto.Request;

import com.jacky.datacollectorservice.model.EMasteryStatus;
import lombok.Data;

@Data
public class UpdateMasteryProcess {
    private EMasteryStatus eMasteryStatus = EMasteryStatus.INCOMPLETE;
}
