package readxml.XMLGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XMLGenerator {
	public static void main(String[] args) {
		XMLGenerator x = new XMLGenerator();
		Map<String, String> globalMap = new HashMap<String, String>();
		globalMap.put("simulation", "segregation");
		globalMap.put("probCatch", "0.25");
		globalMap.put("energy", "10");
		globalMap.put("fishTime", "5");
		globalMap.put("sharkTime", "5");
		globalMap.put("shape", "square");
		int index = 25; //the only parameter that needs to be changed when changing grid size
		int maxStateValue = 3;
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
		return xml.toString();
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
