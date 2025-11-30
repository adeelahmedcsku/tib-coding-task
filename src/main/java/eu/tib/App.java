package eu.tib;

import eu.tib.controller.Operations;
import eu.tib.wrapper.ReportWrapper;
import java.util.List;

public class App {
	/*
	 * New classes can be created in any package if required
	 * Modification of certain classes is not allowed. Read the comments in each class.
	 * Feel free to code here and add custom methods if required
	 */

    private static final String OUTPUT_TASK1 = "task1.csv";
    private static final String OUTPUT_TASK2 = "task2.csv";

	public static void printOnConsole(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {

        try {
            // The following line reads and prints the SampleData.json file from the resource folder
            //Adeel: Task 1 to read the json file SampleData.json
            List<ReportWrapper> reports = Operations.readAndMapJson("SampleData.json");
            Operations.generateTask2CSV(reports, OUTPUT_TASK1);
            Operations.generateTask3CSV(reports, OUTPUT_TASK2, "Berlin");
            printSuccess();
        }
        catch(Exception e){
            
        }
    }



    private static void printSuccess() {

        printOnConsole("ALL TASKS COMPLETED SUCCESSFULLY!");
        printOnConsole("Output Files Generated:");
        printOnConsole(" " + OUTPUT_TASK1 + " - Publications count per year");
        printOnConsole(" " + OUTPUT_TASK2 + " - Berlin publication authors");
        printOnConsole("\n You can now find the CSV files in the project root directory!");
    }
}
