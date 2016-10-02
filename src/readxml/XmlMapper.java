package readxml;

import java.io.File;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import engine.Loop;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import structures.Grid;
import structures.MetaData;
import structures.cell.Animal;
import structures.cell.Cell;
import structures.cell.FireCell;
import structures.cell.SegregationCell;

public class XmlMapper {
	

	/*public static void main(String[] args) {
		XmlMapper xmlmap = new XmlMapper();
		xmlmap.mapXml(myResource.getString("DefaultSelection"));
	}*/
	
	private Map<String, String> globalsMap;
	private MetaData meta;
	private List<Cell> cells;
	private Grid myGrid;
	private Loop myLoop;
	private Properties prop;
	
	public XmlMapper() {
		//this.globalsMap = new HashMap<String, String>();
		this.meta = new MetaData();
		//this.cells = new ArrayList<Cell>();
		this.prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("simulation.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// Maps XML to a grid, loop, and metadata object
	public void mapXml(String filename) {
		this.cells = new ArrayList<Cell>();
		this.globalsMap = new HashMap<String, String>();
		// Get XML File loaded
		File inputFile;
		ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(filename);
		inputFile = new File(classLoader.getResource("xml/"+filename).getFile());

		// DBFactory for parsing XML using DOM method
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		// Create document for DOM parsing
		Document doc = null;
		try {
			doc = dBuilder.parse(inputFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		
		// Get root element of XML file
		Element docElement = doc.getDocumentElement();
		
		// Get list of children of root element & parse into categories
		List<Node> nodes = getListOfChildNodes(docElement);
		Node global = nodes.get(0);
		Node index = nodes.get(1);
		Node squares = nodes.get(2);
		
		// Get all global characteristics
		List<Node> chars = getListOfChildNodes(global);
		for(Node n : chars) {
			List<Node> charChildren = getListOfChildNodes(n);
			String name = charChildren.get(0).getTextContent();
			String value = charChildren.get(1).getTextContent();
			globalsMap.put(name, value);
		}
		
		// Parse number of total cells
		Integer indexValue = Integer.parseInt(index.getTextContent());
		
		// Create grid/array of cells given initial states of each 'cell'
		List<Node> squareList = getListOfChildNodes(squares);
		for(Node square: squareList) {
			List<Node> squareChildren = getListOfChildNodes(square);
			Integer cellIndex = Integer.parseInt(squareChildren.get(0).getTextContent());
			
			Node characteristic = squareChildren.get(1);
			List<Node> squareChars = getListOfChildNodes(characteristic);
//			String sqCharName = squareChars.get(0).getTextContent();
			Integer cellInitialState = Integer.parseInt(squareChars.get(1).getTextContent());
			
			// Create new cells based on type of simulation
			Cell newCell = new Cell();
			
			String simulation = prop.getProperty("simulation");
			if (globalsMap.get(simulation).equals("predator prey")) {
				newCell = createAnimalCell(cellIndex, cellInitialState, globalsMap);
			} else if (globalsMap.get(prop.getProperty("simulation")).equals("fire")) {
				newCell = createFireCell(cellIndex, cellInitialState, globalsMap);
			} else if (globalsMap.get(prop.getProperty("simulation")).equals("segregation")) {
				newCell = createSegregationCell(cellIndex, cellInitialState, globalsMap);
			} else  {
				newCell = createCell(cellIndex, cellInitialState);
			}
			cells.add(newCell);
		}
		
		myGrid = new Grid(cells, (int)Math.sqrt(indexValue), (int)Math.sqrt(indexValue), meta);
		String shape = globalsMap.get(prop.getProperty("cellShape"));
		String wrapping = globalsMap.get(prop.getProperty("gridWrapping"));
		
		meta.setCellShape(myGrid, shape, wrapping);
		meta.setSimulationName(globalsMap.get("simulation"), myGrid);	
		meta.setFileName(filename);	
		myLoop = new Loop(meta, myGrid);
	}
	
	private Cell createCell(Integer cellIndex, Integer cellInitialState){
		return new Cell(cellIndex, cellInitialState);
	}
	
	private Cell createSegregationCell(Integer cellIndex, Integer cellInitialState, Map<String, String> globalsMap){
		return new SegregationCell(cellIndex, 
				cellInitialState, 
				Double.parseDouble(globalsMap.get(prop.getProperty("segregationSatisfactionRate"))));
	}
	private Cell createFireCell(Integer cellIndex, Integer cellInitialState, Map<String, String> globalsMap){
		return new FireCell(cellIndex, 
				cellInitialState, 
				Double.parseDouble(globalsMap.get(prop.getProperty("probabilityOfCatchingFire"))));
	}
	
	private Cell createAnimalCell(Integer cellIndex, Integer cellInitialState, Map<String, String> globalsMap){
		return new Animal(cellIndex, cellInitialState, 
				Integer.parseInt(globalsMap.get(prop.getProperty("energy"))), 
				Integer.parseInt(globalsMap.get(prop.getProperty("fishReproductionTime"))), 
				Integer.parseInt(globalsMap.get(prop.getProperty("sharkReproductionTime")))
				);

	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public MetaData getMeta() {
		return meta;
	}
	
	public Loop getLoop() {
		return myLoop;
	}
	
	
	/*
	 * Returns a list of child nodes of a given node
	 * while parsing out empty #text nodes!
	 */
	private List<Node> getListOfChildNodes(Node n) {
		NodeList nodeList = n.getChildNodes();
		List<Node> listOfNodes = new ArrayList<Node>();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node child = nodeList.item(i);
			if (! (child instanceof Text)) {
				listOfNodes.add(child);
			}
		}
		return listOfNodes;
	}

}
