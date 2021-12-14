package com.unite.challenge.processor;

import com.unite.challenge.comparator.ItemCostDescComparator;
import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class PackageProcessor {
    private final Package aPackage;

    public List<Integer> process() {
        Item[] packs = aPackage.getItems().toArray(new Item[0]);
        Arrays.sort(packs, new ItemCostDescComparator());
        double remain = aPackage.getWeight();
        List<Integer> result = new ArrayList<>();

        int i = 0;
        boolean stopProc = false;
        while (!stopProc) {
            if (i < packs.length - 1 && packs[i].getCost().equals(packs[i + 1].getCost())) {
                if (packs[i].getWeight().compareTo(packs[i + 1].getWeight()) < 0) {
                    remain -= packs[i].getWeight();
                    result.add(packs[i].getIndex());
                } else {
                    remain -= packs[i + 1].getWeight();
                    result.add(packs[i + 1].getIndex());
                    i++;
                }
                i++;
                continue;
            } else {
                if (packs[i].getWeight() <= remain) {
                    remain -= packs[i].getWeight();
                    result.add(packs[i].getIndex());
                    i++;
                } else {
                    i++;
                }
            }

            if (i == packs.length) {
                stopProc = true;
            }
        }
        return result;
    }
}
