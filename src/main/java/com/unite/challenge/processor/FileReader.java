package com.unite.challenge.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class FileReader {

    public static List<String> readFileByLines(String filePath) {
        try {
            return Files
                    .readAllLines(Paths.get(filePath), StandardCharsets.UTF_8).stream()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error(String.format("Error occurred while reading file: %s", filePath));
        }
        return new ArrayList<>();
    }
}
