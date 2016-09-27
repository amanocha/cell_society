package readxml.XMLGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XMLGenerator {
	public static void main(String[] args) {
		XMLGenerator x = new XMLGenerator();
		Map<String, String> globalMap = new HashMap<String, String>();
		globalMap.put("simulation", "game of life");
		globalMap.put("probCatch", "0.25");
		globalMap.put("satisfactionRate", "0.30");
		globalMap.put("energy", "10");
		globalMap.put("fishTime", "1");
		globalMap.put("sharkTime", "10");
		globalMap.put("shape", "square");
		int index = 900; //the only parameter that needs to be changed when changing grid size
		int maxStateValue = 2;
		System.out.println(x.createXML(globalMap, index, maxStateValue));
	}
	public String createXML(Map<String, String> globalMap, int index, int maxStateValue) {
		StringBuilder xml = new StringBuilder();
		
		// BEGIN FILE
		xml.append("<file>");
		
		// GENERATE XML FOR GLOBAL CHARACTERISTICS
		xml.append("<global>");
		for (Entry<String, String> entry : globalMap.entrySet()) {
			xml.append("<chars>");
			xml.append("<name>");
			xml.append(entry.getKey());
			xml.append("</name>");
			xml.append("<value>");
			xml.append(entry.getValue());
			xml.append("</value>");
			xml.append("</chars>");
		}
		xml.append("</global>");
		
		// GENERATE XML FOR INDEX (NUM CELLS)
		xml.append("<index>");
		xml.append(index);
		xml.append("</index>");
		
		// GENERATE XML FOR SQUARES/CELLS + STATES
		xml.append("<squares>");
		for(int i = 0; i < index; i++) {
			xml.append(generateSquareWithRandomState(maxStateValue, i));
		}
		xml.append("</squares>");
		
		// EOF
		xml.append("</file>");
		String xmlString = xml.toString();
		generateFile(xmlString, globalMap, index);
		return xmlString;
	}
	public void generateFile(String text, Map<String, String> globalMap, int index) {
		String simulationName = globalMap.get("simulation");
		String fileName = simulationName + "_" + index + ".xml";
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(text);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String generateSquareWithRandomState(int maxStateValue, int index) {
		StringBuilder square = new StringBuilder();
		int randomNum = (int)Math.floor(Math.random()*maxStateValue);
		square.append("<square>");
		
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
		square.append("</square>");
		return square.toString();
	}
}
