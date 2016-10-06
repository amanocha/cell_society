// This entire file is part of my masterpiece.
// Alan Guo
/**
 * This is part of Alan Guo's  masterpiece (to show what the masterpiece is interfacing).

 * 
 * This XML generator is used by the UserInputToXML masterpiece to generate
 * an XML file based on the parameters set in UserInputToXML.
 * 
 * This is well designed because it has extracted methods that separate the functionality
 * into descriptive methods. It is easy to follow which part of the XML you are generating
 * line by line in the code.
 */

package readxml.XMLGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/*
 * Class that generates xml given inputs
 */
public class XMLGenerator {
	
	private String fileName;
	
	//Method to create the XML
	public String createXML(Map<String, String> globalMap, int index) {
		StringBuilder xml = new StringBuilder();
		
		// BEGIN FILE
		xml.append("<file>");
		
		// GENERATE XML FOR GLOBAL CHARACTERISTICS
		appendGlobalCharacteristics(globalMap, xml);
		
		// GENERATE XML FOR INDEX (NUM CELLS)
		appendNumCells(index, xml);
		
		// GENERATE XML FOR SQUARES/CELLS + STATES
		appendCells(globalMap, index, xml);
		
		// EOF
		xml.append("</file>");
		String xmlString = xml.toString();
		fileName = generateFile(xmlString, globalMap, index);
		return xmlString;
	}

	/*
	 * Appends cells into XML
	 */
	private void appendCells(Map<String, String> globalMap, int index, StringBuilder xml) {
		xml.append("<cells>");
		Integer numStates = Integer.parseInt(globalMap.get("numstates"));
		System.out.println("Num States: "+numStates);
		for(int i = 0; i < index; i++) {
			xml.append(generateSquareWithRandomState(numStates, i));
		}
		xml.append("</cells>");
	}
	
	/*
	 * Appends number of cells in XML
	 */
	private void appendNumCells(int index, StringBuilder xml) {
		xml.append("<index>");
		xml.append(index);
		xml.append("</index>");
	}

	/*
	 * Appends global characteristics into XML
	 */
	private void appendGlobalCharacteristics(Map<String, String> globalMap, StringBuilder xml) {
		xml.append("<globalCharacteristic>");
		for (Entry<String, String> entry : globalMap.entrySet()) {
			xml.append("<globalCharacteristic>");
			xml.append("<key>");
			xml.append(entry.getKey());
			xml.append("</key>");
			xml.append("<value>");
			xml.append(entry.getValue());
			xml.append("</value>");
			xml.append("</globalCharacteristic>");
		}
		xml.append("</globalCharacteristic>");
	}
	
	// Actually writes/generates the XML file
	public String generateFile(String text, Map<String, String> globalMap, int index) {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("simulation.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String simulationName = globalMap.get(prop.getProperty("simulation"));
		fileName = simulationName + "_" + index + ".xml";
		System.out.println("WRITING FILE");
		File file = new File("data/xml/" + fileName);
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	// Generates squares with random state values
	public String generateSquareWithRandomState(int maxStateValue, int index) {
		StringBuilder square = new StringBuilder();
		int randomNum = generateRandomIntUpTo(maxStateValue);
		square.append("<cell>");
		
		appendNumCells(index, square);
		
		square.append("<characteristic>");
		
		square.append("<name>");
		square.append("state");
		square.append("</name>");
		
		square.append("<value>");
		square.append(randomNum);
		square.append("</value>");
		
		square.append("</characteristic>");
		square.append("</cell>");
		return square.toString();
	}

	private int generateRandomIntUpTo(int maxStateValue) {
		return (int)Math.floor(Math.random()*maxStateValue);
	}
}
