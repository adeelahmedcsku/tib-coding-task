package eu.tib;

import eu.tib.controller.Operations;
import eu.tib.model.Report;

import java.util.ArrayList;
import java.util.List;

public class App {
	/*
	 * New classes can be created in any package if required
	 * Modification of certain classes is not allowed. Read the comments in each class.
	 * Feel free to code here and add custom methods if required
	 */
	private static List<Report> reportlist = new ArrayList<Report>();

	public static void printOnConsole(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {
      // The following line reads and prints the SampleData.json file from the resource folder
      printOnConsole(Operations.readJsonFile("SampleData.json").toJSONString());
	}
}
