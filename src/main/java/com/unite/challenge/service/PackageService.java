package com.unite.challenge.service;

import com.unite.challenge.comparator.ItemCostDescComparator;
import com.unite.challenge.converter.PackageConverter;
import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import com.unite.challenge.exception.CustomAppException;
import com.unite.challenge.processor.FileReader;
import com.unite.challenge.util.Constants;
import com.unite.challenge.validator.PackageValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Service class responsible for processing input file containing packages in raw text format
 */
@Slf4j
@RequiredArgsConstructor
public class PackageService {

    private final FileReader fileReader;

    /**
     * Processes the input file
     *
     * @param filePath Path to the input file
     */
    public void processInputFile(String filePath) {
        try {
            fileReader.readTxtFile(filePath).forEach(this::processIndividualLine);
        } catch (IOException | CustomAppException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    /**
     * Converts individual line from input file to Package object, validates and
     * processes to get the result/output
     *
     * @param packageLine individual line representing one package in input file
     */
    private void processIndividualLine(String packageLine) {
        final Package aPackage = PackageConverter.convert(packageLine);
        final PackageValidator packageValidator = new PackageValidator(
                loadProperties(Constants.PROPERTIES_FILE_NAME)
        );
        if (packageValidator.isValid(aPackage)) {
            final List<Integer> result = processPackage(aPackage);
            log.info(result.isEmpty() ? "-" : result.toString());
        } else {
            log.error(packageValidator.getPackageError().toString());
        }
    }

    /**
     * <pre>
     * Algorithm to process the package
     * 1. Sort the items in descending order of the cost
     * 2. Iterate over the sorted list of items
     * 2.1. If the current item is not the last item in the list
     *      and has the same cost as next item, choose the one with less weight
     *      and store its index
     * 2.2. else if the item weight <= remaining package weight, store the index
     * </pre>
     *
     * @param aPackage Package to be processed
     * @return list of item indexes as a result, empty otherwise
     */
    public List<Integer> processPackage(Package aPackage) {
        List<Item> packageItems = aPackage.getItems();
        packageItems.sort(new ItemCostDescComparator());
        double balanceWeight = aPackage.getWeight();
        List<Integer> result = new ArrayList<>();
        int currentIndex = 0;
        while (currentIndex < packageItems.size()) {
            if (isNotLastItemAndHasSameCostAsNextItem(packageItems, currentIndex)) {
                currentIndex = indexOfItemWithLessWeight(packageItems, currentIndex);
                balanceWeight = updateBalanceWeight(packageItems, balanceWeight, result, currentIndex);
            } else if (isItemWeightLessThanBalanceWeight(packageItems, balanceWeight, currentIndex)) {
                balanceWeight = updateBalanceWeight(packageItems, balanceWeight, result, currentIndex);
            }
            currentIndex++;
        }
        return result;
    }

    /**
     * Updates balance weight after reducing by current item weight
     *
     * @param packageItems  list of items in package
     * @param balanceWeight current balance weight
     * @param result        list of resultant item indexes
     * @param index         index of item under processing
     * @return latest balance weight
     */
    private double updateBalanceWeight(
            List<Item> packageItems, double balanceWeight, List<Integer> result, int index
    ) {
        balanceWeight -= packageItems.get(index).getWeight();
        result.add(packageItems.get(index).getIndex());
        return balanceWeight;
    }

    /**
     * Compares two Item objects for weight and returns the one with less weight
     *
     * @param packageItems list of items
     * @param i            current item under processing
     * @return index of the item with less weight
     */
    private Integer indexOfItemWithLessWeight(List<Item> packageItems, Integer i) {
        if (packageItems.get(i).getWeight().compareTo(packageItems.get(i + 1).getWeight()) < 0) {
            return i;
        } else {
            return i + 1;
        }
    }

    /**
     * Checks if it is the last item in the list of items
     *
     * @param currentIndex index of item currently being processed
     * @param lastIndex    index of the last item in the list
     * @return true if item is the last item in the list, false otherwise
     */
    private boolean isLastItem(int currentIndex, int lastIndex) {
        return currentIndex == lastIndex;
    }

    /**
     * Checks if the two items in the list have the same cost
     * the items would be at index i and i+1
     *
     * @param packageItems list of items in a package
     * @param i            index
     * @return true if two items have same cost, false otherwise
     */
    private boolean hasSameCost(List<Item> packageItems, int i) {
        return packageItems.get(i).getCost().equals(packageItems.get(i + 1).getCost());
    }

    /**
     * Checks if the current item weight is less than balance package weight
     *
     * @param packageItems  list of items in a package
     * @param balanceWeight current balance weight
     * @param currentIndex  index of item under processing
     * @return true if current item weight is less than balance package weight, false otherwise
     */
    private boolean isItemWeightLessThanBalanceWeight(List<Item> packageItems, double balanceWeight, int currentIndex) {
        return packageItems.get(currentIndex).getWeight() <= balanceWeight;
    }

    /**
     * Checks if the item is not last in the list and has the same cost as the next item
     *
     * @param packageItems list of items in a package
     * @param currentIndex index of item under processing
     * @return true if the item is not the last in the list and has the same cost as next item,
     * false otherwise
     */
    private boolean isNotLastItemAndHasSameCostAsNextItem(List<Item> packageItems, int currentIndex) {
        return !isLastItem(currentIndex, packageItems.size() - 1) && hasSameCost(packageItems, currentIndex);
    }

    /**
     * Load properties file from resources
     *
     * @return Properties object
     */
    private Properties loadProperties(String propertiesFileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(propertiesFileName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            log.error("Error reading properties file");
        }
        return props;
    }
}
