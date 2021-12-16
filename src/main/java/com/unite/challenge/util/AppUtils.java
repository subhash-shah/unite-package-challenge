package com.unite.challenge.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application specific utility methods
 */
@Slf4j
public class AppUtils {

    /**
     * Parses weight from a line in input file
     *
     * @param line      line from input file
     * @param delimiter used to separate weight from items
     * @return parsed maximum package weight if parseable, -1.0 otherwise
     */
    public static Double parseWeight(String line, String delimiter) {
        if(StringUtils.contains(line, delimiter)) {
            return Double.valueOf(line.split(delimiter)[0].trim());
        }
        return -1.0;
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

    /**
     * Checks if the string is numeric
     * @param strNum numeric string
     * @return true if string is numeric, false otherwise
     */
    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
