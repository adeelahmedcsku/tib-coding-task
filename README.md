# Interview Aufgabe

## Aufgabenbeschreibung

Laden Sie das Java Maven Projekt herunter und richten Sie es in der IDE Ihrer Wahl ein (z.B. Eclipse, IntelliJ, etc.). Der Ressourcen-Ordner des Projekts enthält JSON-strukturierte Metadaten (SampleData.json) von 60 Publikationen. Die Struktur enthält verschiedene Felder, aber für den Zweck dieser Aufgabe werden die folgenden Felder in Betracht gezogen:


1. ppnNo: eindeutiger Bezeichner der Veröffentlichung
2. authorList: Liste aller Autoren der Veröffentlichung
3. Datum der Veröffentlichung: Jahr der Veröffentlichung
4. Ort: Ort der Veröffentlichung

Die Java-Klasse eu.tib.model.Publication enthält die „Getter- und Setter“-Schnittstellen für die oben genannten Felder, die in der Java-Klasse eu.tib.model.Report implementiert sind.
Die Javaklasse eu.tib.controller.Operations enthält die Methoden zum Lesen von JSON-Dateien und zum Schreiben von Textdateien.
Die Javaklasse eu.tib.App enthält die Methode „main“.

## Aufgaben
1. Lies die Datensätze aus „SampleData.json“ mit Hilfe der mitgelieferten Methoden und Modellklassen.
2. Erzeugen Sie eine CSV-Datei mit dem Namen „task1.csv“ mit der Anzahl der Veröffentlichungen in jedem Jahr.

   Die Struktur der Datei sollte wie folgt formatiert sein:

   Jahr, Anzahl  
   1995, 3  
   1990, 5  
   ..., ...

3. Erstellen Sie eine CSV-Datei mit dem Namen „task2.csv“ mit der „ppnNo“ und dem „Nachnamen“ aller „Autoren“ aller Veröffentlichungen, die in „Berlin“ veröffentlicht wurden.

   Die Struktur der Datei sollte wie folgt formatiert sein:  
   SR#, PPN, Autor  
   1, 1008789011, Nachname  
   2, 1008789011, Nachname  
   3, 100872, Nachname
   4, ..., ...


Hinweis: Ihre Lösung muss eine gezippte Kopie des Codes/Projekts und der Ausgabedateien enthalten.
# 📊Developer: Guide Java Data Processing Toolkit  
High-performance JSON parsing, transformation, and CSV generation using Java 17, Streams API, and Jackson.

<p align="left">
  <!-- Badges -->
  <img src="https://img.shields.io/badge/Java-17-blue.svg" />
  <img src="https://img.shields.io/badge/Build-Maven-orange.svg" />
  <img src="https://img.shields.io/badge/License-MIT-green.svg" />
  <img src="https://img.shields.io/badge/Status-Active-success.svg" />
  <img src="https://img.shields.io/badge/Jackson-2.20.0-yellow.svg" />
</p>

---
Jackson: https://www.youtube.com/watch?v=Q0ajO-vqFiw

## 🚀 Overview

This project demonstrates how to process complex JSON data structures and export them into CSV format using:

- **Java 17**
- **Jackson Databind**
- **Java Streams API**
- **Maven**

It includes modular utility classes such as `CsvGenerator`, `Operations`, and POJO models like `Report` and `Author`.

---

## 🧩 Technologies Used

### ✔ Java 17  
Leverages modern Stream APIs (`java.util.stream`) for efficient transformation and grouping operations.

### ✔ Maven  
Used as the build and dependency management tool.

```xml
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```

### Jackon
```xml
<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.20.0</version>
        </dependency>
```


# Interview Task

## Task description

Download the Java Maven project and set it up in the IDE of your choice (i.e., Eclipse, IntelliJ, etc.). The resources folder, of the project, contains JSON structured metadata (SampleData.json) from 60 publications. The structure contains various fields but for the purpose of this task the following fields would be considered:


1. ppnNo: unique identifier of the publication
2. authorList: list of all the authors of the publication
3. publication date: year of the publication
4. place: place of publication

The java class eu.tib.model.Publication contains the “getter and setter” interfaces for the aforementioned fields which are implemented in the Java class eu.tib.model.Report.
The java class eu.tib.controller.Operations contains the methods for reading JSON files and writing text files.
The java class eu.tib.App contains the “main” method.

## Tasks
1. Read the records from “SampleData.json” using the methods and model classes provided.
2. Generate a CSV file named “task1.csv” with the number of publication belonging to each year.

   The structure of the file should be formatted as:

   Year, Count  
   1995, 3  
   1990, 5  
   ..., ...  

3. Generate a CSV file named “task2.csv” with the “ppnNo” and “last name” of all “authors” of all the publications that were published in “Berlin”.

   The structure of the file should be formatted as:  
   SR#, PPN, Author  
   1, 1008789011, last name  
   2, 1008789011, last name  
   3, 1008722222, last name  
   4, ..., ...  


Note: Your solution must include a zipped copy of the code/project and the output files.
