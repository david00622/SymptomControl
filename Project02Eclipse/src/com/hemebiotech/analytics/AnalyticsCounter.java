package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private static int headacheCount = 0;	// initialize to 0
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	public static void main(String args[]) throws Exception {
		//Extract data from file

		ReadSymptomDataFromFile readSymptoms = new ReadSymptomDataFromFile("symptoms.txt");
		ArrayList<String> symptoms = (ArrayList<String>) readSymptoms.GetSymptoms();

		//Create data structure for results count
		Map<String, Integer> resultsSorted = new TreeMap<String, Integer>();

		//Iterate over the gathered list
		for (Iterator<String> it = symptoms.iterator(); it.hasNext();) {

		//Get current element from iterator
		String element = it.next();

		if (!resultsSorted.keySet().contains(element))
		resultsSorted.put(element, 1);

		else
		resultsSorted.put(element, resultsSorted.get(element) + 1);
		}
		
		//Next generate output
		FileWriter writer = new FileWriter("result.out");

		for (Iterator<String> resultsSortedIt = resultsSorted.keySet().iterator(); resultsSortedIt.hasNext();) {

		//Get current element from map
		String key = resultsSortedIt.next();
		Integer value = resultsSorted.get(key);

		//Write Key + Value on the file
		writer.write(key + "=" + value + "\r\n");
		}
		
		writer.close();
	}
}
