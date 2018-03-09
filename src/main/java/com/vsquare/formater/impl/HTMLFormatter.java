package com.vsquare.formater.impl;

import com.vsquare.formater.Formatter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class HTMLFormatter implements Formatter<String, List<Map<String, String>>> {

    @Override
    public String format(String prefix,
                         String suffix,
                         String targetString,
                         List<Map<String, String>> data) {

        String formattedText = data.stream().map(e -> {
            Set<String> keys = e.keySet();
            int idx = 1;
            String formatText = targetString;
            for ( String key : keys ) {
                String value = e.get(key);
                formatText = formatText.replace("${" + idx + "}",
                                                StringUtils.isNotBlank(value) ? value : "");
                idx++;
            }
            return formatText;
        }).collect(joining("\n"));

        return prefix + formattedText + suffix;
    }
}
