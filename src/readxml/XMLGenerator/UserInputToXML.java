package readxml.XMLGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import readxml.XmlMapper;

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
