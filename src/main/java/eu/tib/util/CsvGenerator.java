package eu.tib.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvGenerator {

    private CsvGenerator(){}

    public static void writeCsv(String outputPath, List<String[]> rows)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            for (String[] row : rows) {
                writer.println(String.join(",", row));
            }
            System.out.println("CSV written: " + outputPath);
        } catch (IOException e) {
            System.err.println("ERROR writing CSV: " + e.getMessage());
        }
    }


    public static <T, K> Map<K, Long> groupAndCount(List<T> items, Function<T, K> classifier)
    {
        return items.stream()
                .map(classifier)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        k -> k,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

}