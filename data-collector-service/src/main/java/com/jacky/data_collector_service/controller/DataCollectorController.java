package com.jacky.data_collector_service.controller;

import com.jacky.data_collector_service.model.MasteryProcess;
import com.jacky.data_collector_service.service.DataCollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/datacollector")
@RequiredArgsConstructor
public class DataCollectorController {

    private final DataCollectorService dataCollectorService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void collectData(){
        dataCollectorService.collectData();
    }


}
