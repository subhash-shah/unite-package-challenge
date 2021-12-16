package com.unite.challenge.validator;

import com.unite.challenge.entity.Item;
import com.unite.challenge.util.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Validator for Item entity
 */
@RequiredArgsConstructor
public class ItemValidator {
    @Getter
    private final List<String> errors = new ArrayList<>();
    private final Properties messages;

    /**
     * <pre>
     * Checks if the Item is valid
     * 1. Validates weight of the item
     * 2. Validates cost of the item
     * </pre>
     *
     * @param item Item object to be validated
     * @return true if Item object is valid, false otherwise
     */
    public boolean isValid(Item item) {
        validateItemWeight(item);
        validateItemCost(item);
        return errors.isEmpty();
    }

    /**
     * Validates item cost
     *
     * @param item Item object
     */
    private void validateItemCost(Item item) {
        if (isCostValid(item)) {
            errors.add(
                    MessageFormat.format(
                            messages.getProperty("item.validation.cost.invalid"),
                            item.getIndex(),
                            Constants.MAX_ITEM_COST
                    )
            );
        }
    }

    /**
     * Validates item weight
     *
     * @param item Item object
     */
    private void validateItemWeight(Item item) {
        if (!isWeightInRange(item)) {
            errors.add(
                    MessageFormat.format(
                            messages.getProperty("item.validation.weight.invalid"),
                            item.getIndex(),
                            Constants.MIN_ITEM_WEIGHT,
                            Constants.MAX_ITEM_WEIGHT
                    )
            );
        }
    }

    /**
     * Checks if the item cost is less than maximum item cost allowed
     *
     * @param item Item object
     * @return true if item cost is less than max item cost, false otherwise
     */
    private boolean isCostValid(Item item) {
        return item.getCost() > Constants.MAX_ITEM_COST;
    }

    /**
     * Checks if the item weight is between min, max range (does not allow less than 0.0)
     *
     * @param item Item object
     * @return true if item weight is between range
     */
    private boolean isWeightInRange(Item item) {
        return item.getWeight() >= Constants.MIN_ITEM_WEIGHT
                && item.getWeight() <= Constants.MAX_ITEM_WEIGHT;
    }
}
