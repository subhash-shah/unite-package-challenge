package com.unite.challenge.processor;

import com.unite.challenge.exception.CustomAppException;
import com.unite.challenge.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that validates and reads input txt files.
 */
@Slf4j
@RequiredArgsConstructor
public class FileReader {

    /**
     * Entry method for input file processing (validation and reading)
     *
     * @param filePath Absolute path to the input txt file
     * @return non blank strings
     * @throws CustomAppException – In case file does not exist or empty
     * @throws IOException        – In case of I/O error
     */
    public List<String> readTxtFile(String filePath) throws CustomAppException, IOException {
        Path pathInstance = Paths.get(filePath);
        validateFileExists(pathInstance);
        validateFileType(pathInstance);
        validateFileNotEmpty(pathInstance);
        return Files
                .readAllLines(Paths.get(filePath), StandardCharsets.UTF_8).stream()
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

    /**
     * Validates if the file exists
     *
     * @param pathInstance java.nio.file.Path instance of the input file
     * @throws CustomAppException – In case file does not exist
     */
    private void validateFileExists(Path pathInstance) throws CustomAppException {
        if (!Files.exists(pathInstance)) {
            throw new CustomAppException(String.format("File does not exist at %s", pathInstance));
        }
    }

    /**
     * Validate if the file is plain text file
     *
     * @param pathInstance java.nio.file.Path instance of the input file
     * @throws IOException        – In case of I/O error
     * @throws CustomAppException – In case file is not plain text file
     */
    private void validateFileType(Path pathInstance) throws IOException, CustomAppException {
        final String mimeType = Files.probeContentType(pathInstance);
        if (!Constants.TXT_MIME_TYPE.equals(mimeType)) {
            throw new CustomAppException(String.format("%s is not a text file", pathInstance));
        }
    }

    /**
     * Validates if the file is empty
     *
     * @param pathInstance java.nio.file.Path instance of the input file
     * @throws CustomAppException – In case file is empty
     * @throws IOException        – In case of I/O error
     */
    private void validateFileNotEmpty(Path pathInstance) throws CustomAppException, IOException {
        if (Files.size(pathInstance) == 0) {
            throw new CustomAppException(String.format("File %s is empty", pathInstance));
        }
    }

}
