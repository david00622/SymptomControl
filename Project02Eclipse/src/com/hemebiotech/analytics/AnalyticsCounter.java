package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	
	public static void main(String args[]) throws Exception {
		//Extract data from file
		ReadSymptomDataFromFile readSymptoms = new ReadSymptomDataFromFile("symptoms.txt");
		ArrayList<String> symptoms = (ArrayList<String>) readSymptoms.GetSymptoms();

		//Create data structure for results count
		Map<String, Integer> resultsSorted = new TreeMap<String, Integer>();

		calculateSymp(symptoms, resultsSorted);
		
		generateFile(resultsSorted);
	}

	/**
	 * @param resultsSorted Results list from the calculation
	 */
	private static void generateFile(Map<String, Integer> resultsSorted) throws IOException {
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

	/**
	 * @param symptoms Symptoms list extract from the file 'symptoms.txt'
	 * @param resultsSorted Results list from the calculation
	 */
	private static void calculateSymp(ArrayList<String> symptoms, Map<String, Integer> resultsSorted) {
		//Iterate over the gathered list
		for (Iterator<String> it = symptoms.iterator(); it.hasNext();) {

		//Get current element from iterator
		String element = it.next();

		if (!resultsSorted.keySet().contains(element))
		resultsSorted.put(element, 1);

		else
		resultsSorted.put(element, resultsSorted.get(element) + 1);
		}
	}
}
