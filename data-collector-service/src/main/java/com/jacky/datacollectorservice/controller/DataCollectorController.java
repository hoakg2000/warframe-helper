package com.jacky.datacollectorservice.controller;

import com.jacky.datacollectorservice.model.MasteryProcess;
import com.jacky.datacollectorservice.service.DataCollectorService;
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
