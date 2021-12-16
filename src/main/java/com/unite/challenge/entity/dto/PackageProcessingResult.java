package com.unite.challenge.entity.dto;

import com.unite.challenge.entity.EmptyPackage;
import com.unite.challenge.entity.Package;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * A wrapper class for processing result, simplifies output display
 */
@RequiredArgsConstructor
public class PackageProcessingResult {
    private final Package pckage;
    @Getter
    private final List<Integer> resultIndexes;

    @Override
    public String toString() {
        if (pckage instanceof EmptyPackage || resultIndexes.isEmpty()) {
            return "-";
        } else {
            return resultIndexes.toString().substring(1, resultIndexes.toString().length() - 1);
        }
    }
}
