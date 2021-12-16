package com.unite.challenge.comparator;

import com.unite.challenge.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemCostDescComparatorTest {

    @Test
    @DisplayName("Should sort items in descending order of cost")
    void shouldSortItemsDescCost() {
        Item firstItem = new Item(1, 52.98, 22.98);
        Item secondItem = new Item(2, 22.45, 45.23);
        Item thirdItem = new Item(3, 49.44, 87.43);
        List<Item> items = new ArrayList<>();
        items.add(firstItem);
        items.add(secondItem);
        items.add(thirdItem);
        items.sort(new ItemCostDescComparator());
        assertEquals(3, items.get(0).getIndex());
        assertEquals(2, items.get(1).getIndex());
        assertEquals(1, items.get(2).getIndex());
    }

}