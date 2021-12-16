package com.unite.challenge.converter;

import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import com.unite.challenge.util.AppUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PackageConverter {
    /**
     * Converts raw string (i.e. line from input file) to Package object
     *
     * @param packageLine individual line representing one package in input file
     * @return Package object
     */
    public static Package convert(String packageLine) {
        Double packageWeight = AppUtils.parseWeight(packageLine, ":");
        List<String> parsedData = AppUtils.extractItems(packageLine, "\\((.*?)\\)");
        List<Item> items = parsedData.stream()
                .map(rawPackage -> new Item(rawPackage.split(",")))
                .collect(Collectors.toList());
        return new Package(packageWeight, items);
    }
}
