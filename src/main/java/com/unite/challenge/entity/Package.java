package com.unite.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Entity class package which has a maximum weight and list of items
 */
@AllArgsConstructor
public class Package {
    @Getter
    private Double weight;
    @Getter
    private List<Item> items;
}
