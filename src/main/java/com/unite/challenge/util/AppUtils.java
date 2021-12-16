package com.unite.challenge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application specific utility methods
 */
public class AppUtils {

    /**
     * Parses weight from a line in input file
     *
     * @param line      line from input file
     * @param delimiter used to separate weight from items
     * @return maximum weight for package
     */
    public static Double parseWeight(String line, String delimiter) {
        return Double.valueOf(line.split(delimiter)[0].trim());
    }

    /**
     * Extracts list of items from a line in input file
     *
     * @param line  line from input text file
     * @param regex regular expression to extract items from line
     * @return all extracted items from a line
     */
    public static List<String> extractItems(String line, String regex) {
        List<String> parsedData = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()) {
            parsedData.add(m.group(1));
        }
        return parsedData;
    }
}
