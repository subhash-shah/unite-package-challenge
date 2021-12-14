package com.unite.challenge.service;

import com.unite.challenge.entity.Item;
import com.unite.challenge.entity.Package;
import com.unite.challenge.processor.FileReader;
import com.unite.challenge.processor.PackageProcessor;
import com.unite.challenge.util.AppUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PackageService {

    public void processInputFile(String filePath) {
        List<String> lines = FileReader.readFileByLines(filePath);
        if (!lines.isEmpty()) {
            lines.forEach(this::processLine);
        }
    }

    private void processLine(String line) {
        final PackageProcessor packageProcessor = new PackageProcessor(convertLineToPackage(line));
        final List<Integer> result = packageProcessor.process();
        System.out.println(result.isEmpty() ? "-" : result.toString());
    }

    private Package convertLineToPackage(String line) {
        Double packageWeight = AppUtils.parseWeight(line, ":");
        List<String> parsedData = AppUtils.printSubsInDelimiters(line, "\\((.*?)\\)");
        List<Item> items = parsedData.stream()
                .map(rawPackage -> new Item(rawPackage.split(",")))
                .collect(Collectors.toList());
        return new Package(packageWeight, items);
    }
}
