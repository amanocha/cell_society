/**
 * This is the main part of Alan Guo's code masterpiece.
 * 
 * Purpose of this code is to provide functions for the front end develoepr
 * to take their user input and map set those properties using the endpoints
 * (or methods) from this class. They can set all the properties and then 
 * generate an XML file using this class.
 * 
 * I think that this is well designed because 
 * 1. It has short, succint methods
 * 2. It utilizes a properties file for constants
 * 3. It effectively separates the front end from the XML / backend data generation
 * 4. It provides the front end with an interface to easily set initial parameters
 * 5. It is easily extensible. You can simply add a function in the same format as the others
 * 		if you need to add properties to the global characteristics map
 */
package readxml.XMLGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import readxml.XmlMapper;

/*
 * Takes user input and maps it to the XML Generator
 */
public class UserInputToXML {
	private Map<String, String> globalsMap;
	private int numCells;
	private XMLGenerator xmlGenerator;
	private Properties prop;
	private XmlMapper mapper;
	
	public UserInputToXML(int numCells) {
		this.globalsMap = new HashMap<String, String>();
		this.numCells = numCells;
		this.xmlGenerator = new XMLGenerator();
		this.mapper = new XmlMapper();
		this.prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("simulation.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Adds key value pair to the map
	public void addParameter(String key, String value) {
		globalsMap.put(key, value);
	}
	
	/*
	 * All following set methods add key/value pairs to the global map
	 */
	public void setMaxStates(int numStates) {
		addParameter(prop.getProperty("maxStates"), Integer.toString(numStates));
	}
	
	public void setSimulation(String simulationName) {
		addParameter(prop.getProperty("simulation"), simulationName);
	}
	
	public void setProbCatch(double prob) {
		addParameter(prop.getProperty("probabilityOfCatchingFire"), Double.toString(prob));
	}
	
	public void setShape(String shape) {
		addParameter(prop.getProperty("cellShape"), shape);
	}
	
	public void setWrapping(String wrapping) {
		addParameter(prop.getProperty("gridWrapping"), wrapping);
	}
	
	public void setSatisfactionRate(double rate) {
		addParameter(prop.getProperty("segregationSatisfactionRate"), Double.toString(rate));
	}
	
	public void setEnergy(int energy) {
		addParameter(prop.getProperty("energy"), Integer.toString(energy));
	}
	
	public void setFishReproductionTime(int time) {
		addParameter(prop.getProperty("fishReproductionTime"), Integer.toString(time));
	}
	
	public void setSharkReproductionTime(int time) {
		addParameter(prop.getProperty("sharkReproductionTime"), Integer.toString(time));
	}
	
	public void setInitSugar(int initSugar) {
		addParameter(prop.getProperty("initSugar"), Integer.toString(initSugar));
	}
	
	public void setSugarGrowBackTime(int time) {
		addParameter(prop.getProperty("sugarGrowBackTime"), Integer.toString(time));
	}
	
	public void setVision(int vision) {
		addParameter(prop.getProperty("vision"), Integer.toString(vision));
	}
	
	public void setSugarMetabolism(int sugarMetabolism) {
		addParameter(prop.getProperty("sugarMetabolism"), Integer.toString(sugarMetabolism));
	}
	
	// Generate XML based on values in map
	public void generateXML() {
		xmlGenerator.createXML(globalsMap, numCells);
		mapper.mapXml(xmlGenerator.getFileName());
	}
	
	public XmlMapper getMapper() {
		return mapper;
	}
	
	// For debugging
	public void printGlobalsMap() {
		for (Entry<String, String> entry : globalsMap.entrySet()) {
		    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
	}

}
