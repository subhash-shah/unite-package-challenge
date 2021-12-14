package com.unite.challenge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public static Double parseWeight(String line, String delimiter) {
        return Double.valueOf(line.split(delimiter)[0].trim());
    }

    public static List<String> printSubsInDelimiters(String str, String regex) {
        List<String> parsedData = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            parsedData.add(m.group(1));
        }
        return parsedData;
    }
}
