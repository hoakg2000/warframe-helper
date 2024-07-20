package com.jacky.data_collector_service.dto;

import jakarta.annotation.Resource;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weapon {
    private String uniqueName;

    private String name;

    private String description;

    private String category;

    private String imageName;

    private Integer masteryReq = 0;

    private Integer maxLevelCap;

    private Boolean isComponent = false;

    private Set<Weapon>  weaponComponent;

    private Set<Resource>  resourceComponent;

}
