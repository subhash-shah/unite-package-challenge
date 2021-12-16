package com.unite.challenge.factory;

import com.unite.challenge.entity.EmptyPackage;
import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;

import java.util.List;

/**
 * Factory implementation for Package class
 * Creates Pacakge or EmptyPackage
 */
public class PackageFactory {
    /**
     * Creates Package/EmptyPackage object based on package weight and list of items
     * @param weight parsed package weight
     * @param items parsed items
     * @return Package/EmptyPackage object
     */
    public static Package createPackage(Double weight, List<Item> items) {
        if(weight <= 0.0 || items.isEmpty()) {
            return new EmptyPackage();
        }
        return new Package(weight, items);
    }
}
