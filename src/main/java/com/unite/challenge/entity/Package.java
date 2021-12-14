package com.unite.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Package {
    @Getter
    private Double weight;
    @Getter
    private List<Item> items;
}
