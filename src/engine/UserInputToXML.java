package engine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import readxml.XMLGenerator.XMLGenerator;

public class UserInputToXML {
	private Map<String, String> globalsMap;
	private int numCells;
	private int numStates;
	private XMLGenerator xmlGenerator;
	Properties prop;
	
	public UserInputToXML(int numCells) {
		this.globalsMap = new HashMap<String, String>();
		this.numCells = numCells;
		this.numStates = numStates;
		this.xmlGenerator = new XMLGenerator();
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
	
	public void setSimulation(String simulationName) {
		addParameter(prop.getProperty("simulation"), simulationName);
	}
	
	public void setProbCatch(double prob) {
		addParameter(prop.getProperty("probabilityOfCatchingFire"), Double.toString(prob));
	}
	
	public void setShape(String shape) {
		addParameter(prop.getProperty("cellShape"), shape);
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
		addParameter(prop.getProperty("setSharkReproductionTime"), Integer.toString(time));
	}
	
	// Generate XML based on values in map
	public void generateXML() {
		xmlGenerator.createXML(globalsMap, numCells, numStates);
	}
	
	// For debugging
	public void printGlobalsMap() {
		for (Entry<String, String> entry : globalsMap.entrySet()) {
		    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
	}

}
