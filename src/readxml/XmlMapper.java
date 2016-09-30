package readxml;

import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import structures.Animal;
import structures.Cell;
import structures.FireCell;
import structures.Grid;
import structures.MetaData;
import structures.SegregationCell;

public class XmlMapper {

	/*public static void main(String[] args) {
		XmlMapper xmlmap = new XmlMapper();
		xmlmap.mapXmlToGrid("GameOfLife.xml");
	}*/
	
	private Map<String, String> globalsMap;
	private MetaData myMeta;
	private List<Cell> cells;
	private Grid myGrid;
	private Loop myLoop;
	
	public XmlMapper() {
	}
	
	public void mapXmlToGrid(String filename) {
		
		this.globalsMap = new HashMap<String, String>();
		myMeta = new MetaData();
	
		File inputFile;
		ClassLoader classLoader = getClass().getClassLoader();
		inputFile = new File(classLoader.getResource(filename).getFile());

		// DBFactory for parsing XML using DOM method
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create document for DOM parsing
		Document doc = null;
		try {
			doc = dBuilder.parse(inputFile);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
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
			//System.out.println("name: "+name);
			//System.out.println("value : " + value);
			globalsMap.put(name, value);
		}
		
		// Get number of total cells
		Integer indexValue = Integer.parseInt(index.getTextContent());
		//System.out.println("Index value: "+indexValue);
		
		// Initialize 1-d array of cells
		cells = new ArrayList<Cell>();
		
		// Create grid/array of cells given initial states of each 'square'
		List<Node> squareList = getListOfChildNodes(squares);
		for(Node square: squareList) {
			List<Node> squareChildren = getListOfChildNodes(square);
			Integer squareIndex = Integer.parseInt(squareChildren.get(0).getTextContent());
			
			Node characteristic = squareChildren.get(1);
			List<Node> squareChars = getListOfChildNodes(characteristic);
			String sqCharName = squareChars.get(0).getTextContent();
			Integer sqCharValue = Integer.parseInt(squareChars.get(1).getTextContent());
			
			// MUST CHANGE BELOW LINE TO PASS IN RIGHT CONSTRUCTOR PARAMS!!
			Cell newCell = new Cell();
			if (globalsMap.get("simulation").equals("predator prey")) {
				newCell = new Animal(squareIndex, sqCharValue, Integer.parseInt(globalsMap.get("energy")), Integer.parseInt(globalsMap.get("fishTime")), Integer.parseInt(globalsMap.get("sharkTime")));
			} else if (globalsMap.get("simulation").equals("fire")) {
				newCell = new FireCell(squareIndex, sqCharValue, Double.parseDouble(globalsMap.get("probCatch")));
			} else if (globalsMap.get("simulation").equals("segregation")) {
				newCell = new SegregationCell(squareIndex, sqCharValue, Double.parseDouble(globalsMap.get("satisfactionRate")));
			} else  {
				newCell = new Cell(squareIndex, sqCharValue);
			}
			cells.add(newCell);
		}
		
		// Testing cell creation
		for(int i = 0; i < cells.size(); i++) {
			Cell currCell = cells.get(i);
			//System.out.println(currCell.getNumber());
		}

		String shape = "square";
		//HARD CODED SHAPE AND GRID ROWS AND COLS
		//Grid cellGrid = new Grid(cells, 6, 6, myMeta); //only change dimensions
		for (String key : globalsMap.keySet()) {
			//System.out.println(key);
		}
		
		myGrid = new Grid(cells, (int)Math.sqrt(indexValue), (int)Math.sqrt(indexValue), myMeta);
		myMeta.setCellShape(shape, myGrid);
		myMeta.setFileName(filename);
		myMeta.setSimulationName(globalsMap.get("simulation"), myGrid);	
		myLoop = new Loop(myMeta, myGrid);
	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public MetaData getMeta() {
		return myMeta;
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
