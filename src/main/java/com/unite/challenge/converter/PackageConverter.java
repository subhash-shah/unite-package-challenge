package com.unite.challenge.converter;

import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import com.unite.challenge.factory.PackageFactory;
import com.unite.challenge.util.AppUtils;
import com.unite.challenge.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
                .map(PackageConverter::mapToItem)
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        return PackageFactory.createPackage(packageWeight, items);
    }

    /**
     * Maps string to Item object
     * @param rawItem item in string representation
     * @return Item object if valid format, null otherwise
     */
    private static Item mapToItem(String rawItem) {
        String[] itemValues = rawItem.split(Constants.ITEM_VALUES_SEPARATOR);
        if(itemValues.length == 3 && itemValues[2].trim().startsWith(Constants.CURRENCY_SYMBOL)) {
            Item item = new Item();
            if(AppUtils.isNumeric(itemValues[0].trim())) {
                item.setIndex(Integer.parseInt(itemValues[0].trim()));
            }
            if(AppUtils.isNumeric(itemValues[1].trim())) {
                item.setWeight(Double.parseDouble(itemValues[1].trim()));
            }
            if(AppUtils.isNumeric(itemValues[2].trim().substring(1))) {
                item.setCost(Double.parseDouble(itemValues[2].trim().substring(1)));
            }
            return item;
        } else {
            log.error(String.format("%s is in invalid item format", rawItem));
        }
        return null;
    }
}
