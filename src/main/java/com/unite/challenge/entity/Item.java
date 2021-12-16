package com.unite.challenge.entity;

import lombok.*;

/**
 * Entity class for individual Item in Package
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Getter
    @Setter
    private int index;
    @Getter
    @Setter
    private Double weight;
    @Getter
    @Setter
    private Double cost;
}
