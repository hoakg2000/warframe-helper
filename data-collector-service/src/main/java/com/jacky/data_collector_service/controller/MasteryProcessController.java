package com.jacky.data_collector_service.controller;

import com.jacky.data_collector_service.dto.Request.CreateMastery;
import com.jacky.data_collector_service.dto.Request.UpdateMasteryProcess;
import com.jacky.data_collector_service.dto.masteyprocessDto.MasteryProcessDto;
import com.jacky.data_collector_service.model.EMasteryStatus;
import com.jacky.data_collector_service.model.MasteryProcess;
import com.jacky.data_collector_service.service.MasteryProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mastery")
@RequiredArgsConstructor
public class MasteryProcessController {

    private final MasteryProcessService masteryProcessService;
    @GetMapping("/account/{accountid}")
    public List<MasteryProcessDto> getMasteryProcess(@PathVariable String accountid){
        return masteryProcessService.getMasteryProcess(accountid);
    }

    @PostMapping("/account/{accountid}")
    public void createNewMasteryProcess(@PathVariable String accountid){
        masteryProcessService.createNewMasteryProcess(accountid);
    }

    @PatchMapping("/account/{accountid}/masterystatus/{statusid}")
    public void updateMasteryProcessStatus(@PathVariable Long id,
                                           @PathVariable Long statusid,
                                           @RequestBody EMasteryStatus masteryStatus){
        masteryProcessService.updateMasteryProcessStatus(id, masteryStatus);
    }


}
