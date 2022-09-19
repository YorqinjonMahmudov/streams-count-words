package com.epam.rd.autotasks;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Words {


    public String countWords(List<String> lines) {

        Stream<String> stringStream = lines.stream()
                .flatMap(Pattern.compile("[^\\p{L}|^\\d]+")::splitAsStream)
                .filter(s -> s.length() >= 4)
                .map(String::toLowerCase);

        Map<String, Integer> map = new TreeMap<>();
        stringStream.
                forEach(s -> {
                    Integer val = map.getOrDefault(s, 0);
                    map.put(s, 1 + val);
                });

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        StringBuilder str = new StringBuilder();
        entries.stream()
                .filter(s -> s.getValue() >= 10)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(s -> str.append(s.getKey())
                        .append(" - ")
                        .append(s.getValue())
                        .append("\n"));

        return str.deleteCharAt(str.length() - 1).toString();
    }


}

