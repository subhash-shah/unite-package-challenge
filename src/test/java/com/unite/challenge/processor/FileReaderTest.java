package com.unite.challenge.processor;

import com.unite.challenge.exception.CustomAppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
    FileReader fileReader = new FileReader();
    String nonExistentFile = "docs\\nofile.txt";
    String nonTextFile = "docs\\nonTextFile.doc";
    String emptyTextFile = "docs\\emptyTextFile.txt";
    String validInputFile = "docs\\sampleInput.txt";


    @Test
    @DisplayName("Should throw exception if file does not exist")
    void shouldThrowExceptionIfFileDoesNotExist() {
        assertThrows(CustomAppException.class, () -> fileReader.readTxtFile(nonExistentFile), String.format("File does not exist at %s", nonExistentFile));
    }

    @Test
    @DisplayName("Should throw exception if file is not plan text file")
    void shouldThrowExceptionIfFileNotTextFile() {
        assertThrows(CustomAppException.class, () -> fileReader.readTxtFile(nonTextFile), String.format("%s is not a text file", nonTextFile));
    }

    @Test
    @DisplayName("Should throw exception if file is empty")
    void shouldThrowExceptionIfFileIsEmpty() {
        final CustomAppException customAppException = assertThrows(CustomAppException.class, () -> fileReader.readTxtFile(emptyTextFile));
        assertEquals(String.format("File %s is empty", emptyTextFile), customAppException.getLocalizedMessage());
    }

    @Test
    @DisplayName("Should read file correctly")
    void shouldReadFile() throws CustomAppException, IOException {
        final List<String> lines = fileReader.readTxtFile(validInputFile);
        assertFalse(lines.isEmpty());
        assertEquals(4, lines.size());
        assertTrue(lines.get(0).startsWith("81"));
        assertTrue(lines.get(3).startsWith("56"));
    }
}