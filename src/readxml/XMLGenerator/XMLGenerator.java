package readxml.XMLGenerator;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class XMLGenerator {
	
	private String fileName;
//	public static void main(String[] args) {
//		XMLGenerator x = new XMLGenerator();
//		Map<String, String> globalMap = new HashMap<String, String>();
//		globalMap.put("simulation", "game of life");
//		globalMap.put("probCatch", "0.25");
//		globalMap.put("satisfactionRate", "0.30");
//		globalMap.put("energy", "10");
//		globalMap.put("fishTime", "1");
//		globalMap.put("sharkTime", "10");
//		globalMap.put("shape", "square");
//		int index = 900; //the only parameter that needs to be changed when changing grid size
//		int maxStateValue = 2;
//		System.out.println(x.createXML(globalMap, index, maxStateValue));
//	}
	public String createXML(Map<String, String> globalMap, int index) {
		StringBuilder xml = new StringBuilder();
		
		// BEGIN FILE
		xml.append("<file>");
		
		// GENERATE XML FOR GLOBAL CHARACTERISTICS
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
		
		// GENERATE XML FOR INDEX (NUM CELLS)
		xml.append("<index>");
		xml.append(index);
		xml.append("</index>");
		
		// GENERATE XML FOR SQUARES/CELLS + STATES
		xml.append("<cells>");
		Integer numStates = Integer.parseInt(globalMap.get("numstates"));
		System.out.println("Num States: "+numStates);
		for(int i = 0; i < index; i++) {
			xml.append(generateSquareWithRandomState(numStates, i));
		}
		xml.append("</cells>");
		
		// EOF
		xml.append("</file>");
		String xmlString = xml.toString();
		fileName = generateFile(xmlString, globalMap, index);
		return xmlString;
	}
	
	
	public String generateFile(String text, Map<String, String> globalMap, int index) {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("simulation.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String simulationName = globalMap.get(prop.getProperty("simulation"));
		fileName = simulationName + "_" + index + ".xml";
		File file = new File("data/xml/"+fileName);
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.println(text);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
	

	public String generateSquareWithRandomState(int maxStateValue, int index) {
		StringBuilder square = new StringBuilder();
		int randomNum = (int)Math.floor(Math.random()*maxStateValue);
		square.append("<cell>");
		
		square.append("<index>");
		square.append(index);
		square.append("</index>");
		
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
}
