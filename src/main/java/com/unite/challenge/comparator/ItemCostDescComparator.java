package com.unite.challenge.comparator;

import com.unite.challenge.entity.Item;

import java.util.Comparator;

/**
 * Comparator for Item objects based on Item#cost
 */
public class ItemCostDescComparator implements Comparator<Item> {
    @Override
    public int compare(Item firstItem, Item secondItem) {
        return secondItem.getCost().compareTo(firstItem.getCost());
    }
}
