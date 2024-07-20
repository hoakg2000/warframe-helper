package com.jacky.datacollectorservice.comparators;

import com.jacky.datacollectorservice.model.Item;

import java.util.Comparator;

public class WeaponPiorityComparators implements Comparator<Item> {
    public int compare(Item a, Item b) {

        //check which one is component of other weapon
        if (a.getIsComponent() != b.getIsComponent()){
            if (a.getIsComponent()){
                return 1;
            }else{
                return -1;
            }
        }

        return a.getMasteryReq().compareTo(b.getMasteryReq());
    }
}
