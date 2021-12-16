package com.unite.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * Entity class for individual Item in Package
 */
@ToString
@AllArgsConstructor
public class Item {
    @Getter
    private int index;
    @Getter
    private Double weight;
    @Getter
    private Double cost;

    public Item(String[] itemValues) {
        if (StringUtils.isNotBlank(itemValues[0])) {
            this.index = Integer.parseInt(itemValues[0]);
        }

        if (StringUtils.isNotBlank(itemValues[1])) {
            this.weight = Double.parseDouble(itemValues[1]);
        }

        if (StringUtils.isNotBlank(itemValues[2])) {
            this.cost = Double.parseDouble(itemValues[2].split("€")[1]);
        }
    }
}
