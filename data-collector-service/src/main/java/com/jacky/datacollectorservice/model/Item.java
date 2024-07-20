package com.jacky.datacollectorservice.model;

import com.fasterxml.jackson.annotation.*;
import com.jacky.datacollectorservice.dto.ItemDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Comparator<Item> {
    @Id
    @Column(name = "unique_name")
    private String uniqueName;

    private String name;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    private Boolean masterable = false;

    @Builder.Default
    private Boolean tradable = false;

    private String type;

    private String imageName;

    @Builder.Default
    private Integer masteryReq = 0;

    @Builder.Default
    private Boolean isPrime = false;

    private Integer maxLevelCap;

    @Builder.Default
    private Boolean isComponent = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "unique_name")
    private List<DropSource> drops;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Recipe> components;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MasteryProcess> masteryProcesses;

    public Item(ItemDto itemDto){
        this.setUniqueName(itemDto.getUniqueName());
        this.setName(itemDto.getName());
        this.setCategory(itemDto.getCategory());
        this.setDescription(itemDto.getDescription());
        this.setMasterable(itemDto.getMasterable());
        this.setTradable(itemDto.getTradable());
        this.setType(itemDto.getType());
        this.setImageName(itemDto.getImageName());
        this.setIsComponent(itemDto.getIsComponent());
        this.setMasteryReq(itemDto.getMasteryReq());
        this.setIsPrime(itemDto.getIsPrime());
        this.setMaxLevelCap(itemDto.getMaxLevelCap());
        this.setDrops(itemDto.getDrops());
        this.setDefaultValue();
    }

    public void setDrops(List<DropSource> drops){
        if (drops == null){
            this.drops = new ArrayList<>();
            return;
        }
        this.drops = drops;
    }


    public void setDefaultValue(){
        //If item can levelup and gain xp then set default maxLevelCap is 30
        //Data source already set maxLevelCap for some specific item
        if (this.masterable == true && this.maxLevelCap == null){
            this.maxLevelCap = 30;
        }
    }

    public void removeNotImportantField(){
        this.setDescription(null);
        this.setMasterable(null);
        this.setTradable(null);
        this.setIsComponent(null);
        this.setMasteryReq(null);
        this.setIsPrime(null);
        this.setMaxLevelCap(null);
        this.drops = null;
        this.setComponents(null);
    }

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getMasteryReq().compareTo(o2.getMasteryReq());
    }


    @Override
    public String toString(){
        return String.format("MR %d - %s", this.getMasteryReq(), this.getName());
    }

}
