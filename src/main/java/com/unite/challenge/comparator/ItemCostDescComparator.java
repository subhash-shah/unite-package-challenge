package com.unite.challenge.comparator;

import com.unite.challenge.entity.Item;

import java.util.Comparator;

public class ItemCostDescComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o2.getCost().compareTo(o1.getCost());
    }
}
