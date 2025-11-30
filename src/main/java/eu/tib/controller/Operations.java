package eu.tib.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.tib.dto.AuthorDto;
import eu.tib.dto.PublicationDto;
import eu.tib.model.Author;
import eu.tib.model.Report;
import eu.tib.util.CsvGenerator;
import eu.tib.wrapper.ReportWrapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// Note: Feel free to code here, and add custom methods if required.

public class Operations {

    private Operations() {
    }

    /*
    If for some reason the application is not able to read the JSON file from the resource directory,
    place the JSON file on a custom path and remove/modify the first two lines of the following method as needed.
    */

    public static List<ReportWrapper> readAndMapJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PublicationDto> publicationDtoList = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new FileNotFoundException("Resource '" + filePath + "' not found in classpath.");
            }
            publicationDtoList = objectMapper.readValue(inputStream, new TypeReference<>() {
            });

        } catch (Exception e) {
            System.err.println("Error reading or parsing JSON file: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }

        return convertDtosToReportWrappers(publicationDtoList);
    }

    public static List<ReportWrapper> convertDtosToReportWrappers(List<PublicationDto> publicationDtoList) {
        List<ReportWrapper> wrappers = new ArrayList<>();

        for (PublicationDto publication : publicationDtoList) {
            Report report = new Report();
            String originalPpnString = "";
            if (publication.getPpnNo() != null && !publication.getPpnNo().isEmpty()) {
                originalPpnString = publication.getPpnNo().get(0); // CAPTURE RAW STRING
                try {
                  /*  Adeel: it will fail for non-numeric PPNs ('X')
                     and will return the null or empty here but to eradicate this issue. I have created wrapper
                     so that i can preserve the actual value of the ppnNo.
                  */
                    report.setPpn(Long.parseLong(originalPpnString));
                } catch (NumberFormatException e) {
                    System.err.println("Warning: Could not parse PPN to long for Report object: " + originalPpnString);
                    report.setPpn(-1);
                }
            }

            if (publication.getPublicationDate() != null) {
                try {
                    report.setPublicationYear(Integer.parseInt(publication.getPublicationDate().trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Warning: Could not parse publication date to int: " + publication.getPublicationDate());
                    report.setPublicationYear(0);
                }
            }
            report.setPubLocation(publication.getPlace());
            List<Author> authors = new ArrayList<>();
            if (publication.getAuthorList() != null) {
                for (AuthorDto authorDto : publication.getAuthorList()) {
                    if (authorDto.getAuthor() != null) {
                        Author author = parseAuthorName(authorDto.getAuthor());
                        if (author.getLastName() != null) {
                            authors.add(author);
                        }
                    }
                }
            }
            report.setAuthors(authors);

            wrappers.add(new ReportWrapper(report, originalPpnString));
        }
        return wrappers;
    }

    public static void generateTask2CSV(List<ReportWrapper> reports, String outputPath) {
        Map<Integer, Long> publicationYearCounts = CsvGenerator.groupAndCount(reports,
                wrapper -> wrapper.report().getPublicationYear());
        // 2)Adeel: Convert to CSV rows using streams
        List<String[]> rowsTask2Csv = publicationYearCounts.entrySet().stream()
                .map(publicationYearCount -> new String[]{
                        String.valueOf(publicationYearCount.getKey()),
                        String.valueOf(publicationYearCount.getValue())
                })
                .sorted((publicationYearCount1, publicationYearCount2) ->  publicationYearCount2[0].compareTo(publicationYearCount1[0]))
                .collect(Collectors.toCollection(ArrayList::new));

        //Adeel: Add header at index 0
        rowsTask2Csv.add(0, new String[]{"Year", "Count"});
        //Adeel: Write the csv using generic utility
        CsvGenerator.writeCsv(outputPath, rowsTask2Csv);

        System.out.println("Task 2 completed");
    }

    public static void generateTask3CSV(List<ReportWrapper> reports, String outputPath, String filterCity) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"SR#", "PPN", "Author"});
        final int[] serialNumber = {1};

        reports.stream()
                .filter(wrapper -> filterCity.equalsIgnoreCase(wrapper.report().getPubLocation()))
                // 2) Flatten the list of authors for each Report
                .flatMap(wrapper -> wrapper.report().getAuthor().stream()
                        .map(author -> new AbstractMap.SimpleEntry<>(wrapper.originalPpnString(), author)))
                // 3) Process and collect the required data
                .forEach(entry -> {
                    String ppnString = entry.getKey(); // <-- THIS IS THE RAW STRING (e.g., "100892976X")
                    Author author = entry.getValue();
                    rows.add(new String[]{
                            String.valueOf(serialNumber[0]++),
                            ppnString,
                            (author.getLastName() != null && !author.getLastName().trim().isEmpty())
                                    ? author.getLastName()
                                    : (author.getFirstName() != null && !author.getFirstName().trim().isEmpty())
                                    ? author.getFirstName() : "Name is not Available"
                    });
                });

        // 2) Write CSV
        CsvGenerator.writeCsv(outputPath, rows);

        System.out.println(" Task 2 completed (" + (rows.size() - 1) + " author entries)");
    }


    private static Author parseAuthorName(String fullName) {
        Author author = new Author();
        if (fullName == null || fullName.trim().isEmpty()) {
            return author;
        }
        String trimmed = fullName.trim();
        // Format: "LastName, FirstName"
        if (trimmed.contains(",")) {
            String[] parts = trimmed.split(",", 2);
            author.setLastName(parts[0].trim());
            if (parts.length > 1) {
                author.setFirstName(parts[1].trim());
            }
        } else {
            String[] parts = trimmed.split("\\s+");
            if (parts.length >= 2) {
                // Last part is last name
                author.setLastName(parts[parts.length - 1]);
                // Everything else is first name
                author.setFirstName(String.join(" ",
                        Arrays.copyOfRange(parts, 0, parts.length - 1)));
            } else {
                // Single name - treat as last name
                author.setLastName(parts[0]);
            }
        }

        return author;
    }
}
