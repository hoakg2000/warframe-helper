package com.jacky.data_collector_service.service;

import com.jacky.data_collector_service.comparators.WeaponPiorityComparators;
import com.jacky.data_collector_service.dto.Request.UpdateMasteryProcess;
import com.jacky.data_collector_service.dto.masteyprocessDto.ItemDto;
import com.jacky.data_collector_service.dto.masteyprocessDto.MasteryProcessDto;
import com.jacky.data_collector_service.dto.masteyprocessDto.RecipeDto;
import com.jacky.data_collector_service.model.*;
import com.jacky.data_collector_service.repository.ItemRepository;
import com.jacky.data_collector_service.repository.MasteryProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasteryProcessService {

    private final ItemRepository itemRepository;

    private final MasteryProcessRepository masteryProcessRepository;

    public List<MasteryProcessDto> getMasteryProcess(String accountid){
        List<MasteryProcess> masteryProcesses = masteryProcessRepository.findAllByAccountId(accountid);
        return masteryProcesses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void createNewMasteryProcess(String accountid){
        List<Item> weapons = itemRepository.findByCategoryInAndTypeNotIn(ECategory.getListWeaponCategory(), Arrays.asList("Companion Weapon","Zaw Component"));

        Collections.sort(weapons, new WeaponPiorityComparators());

        for (int i = 0; i<weapons.size(); i++){
            Item weapon = weapons.get(i);
            MasteryProcess masteryProcess = MasteryProcess.builder()
                    .accountId(accountid)
                    .item(weapon)
                    .eMasteryStatus(EMasteryStatus.INCOMPLETE)
                    .priorityLevel(i+1)
                    .build();
            masteryProcessRepository.save(masteryProcess);
        }
    }

    public void updateMasteryProcessStatus(Long id, EMasteryStatus masteryStatus){
        Optional<MasteryProcess> masteryProcess = masteryProcessRepository.findById(id);
        if (masteryProcess.isEmpty()){
            return;
        }

        MasteryProcess updateMasteryProcess = masteryProcess.get().setEMasteryStatus(masteryStatus);
        masteryProcessRepository.save(updateMasteryProcess);
    }

    public void deleteMasteryProcess(String accountid){
        masteryProcessRepository.deleteByAccountId(accountid);
    }



    private MasteryProcessDto convertToDTO(MasteryProcess masteryProcess){
        ItemDto itemDto = ItemDto.builder()
                .name(masteryProcess.getItem().getName())
                .masteryReq(masteryProcess.getItem().getMasteryReq())
                .components(convertToListRecipeDto(masteryProcess.getItem().getComponents()))
                .imageName(masteryProcess.getItem().getImageName())
                .uniqueName(masteryProcess.getItem().getUniqueName())
                .category(masteryProcess.getItem().getCategory())
                .build();

        return MasteryProcessDto.builder()
                .id(masteryProcess.getId())
                .eMasteryStatus(masteryProcess.getEMasteryStatus())
                .item(itemDto)
                .priorityLevel(masteryProcess.getPriorityLevel())
                .accountId(masteryProcess.getAccountId())
                .build();
    }

    private List<RecipeDto> convertToListRecipeDto(List<Recipe> recipes){
        if (recipes == null){
            return null;
        }
        return recipes.stream().map(RecipeDto::new).collect(Collectors.toList());
    }

}
