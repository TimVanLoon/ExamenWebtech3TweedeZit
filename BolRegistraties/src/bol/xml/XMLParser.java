package bol.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

import java.io.*;


public class XMLParser {
	
	// ZEKER DE PATHING AANPASSEN
	private String INPUTFILE = "/Users/Tim/Documents/Github/ExamenWebtech3TweedeZit/BolRegistraties/bestellingen.xml";
	
	/** Get all races from the xml file and return them 
	 * in html format
	 */
	public String getRegistraties() {
		File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        String result = "<h2>Bestellingen</h2>";
	        
	        NodeList registraties = doc.getElementsByTagName("registratie");

	        for (int i = 0; i < registraties.getLength(); i++) {
	        	 Node nNode = registraties.item(i);
	        	 Element eElement = (Element) nNode;
	        	 
	        	 result += "<br/><b>Naam klant : </b>" + eElement.getAttribute("klantNaam");
	        	 result += "<br/><b>Adres : </b>" + eElement.getAttribute("adres");
	        	 result += "<br/><b>Datum bestelling : </b>" + eElement.getAttribute("datumBestelling");
	        	 result += "<br/><b>Produktnaam : </b>" + eElement.getAttribute("produktNaam");
	        	 result += "<br/><b>Hoeveelheid : </b>" + eElement.getAttribute("hoeveelheid");
	        	 result += "<br/>";
	        	 result += "<br/>";
	        }

	        return result;
		} 
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	
	public String addRegistratie(String registratie) {
		File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // set validating false to enable copying
        // node from one document to another
        dbFactory.setValidating(false);
        DocumentBuilder dBuilder;
        
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc1 = dBuilder.parse(inputFile);
	        Document doc2 = dBuilder.parse(new InputSource(new StringReader(registratie)));
	        Element element = (Element) doc2.getDocumentElement();
	        // imports a node from doc2 document to doc1, without altering
	        // or removing the source node from the original document
	        Node copiedNode = doc1.importNode(element, true);
	        // adds the node to the end of the list of children of this node
	        doc1.getDocumentElement().appendChild(copiedNode);
	        
	        /*FileWriter fw = new FileWriter(INPUTFILE);
	        fw.write(doc1.toString());
	        fw.close();*/
	        // write the new document to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc1);
			StreamResult result = new StreamResult(new File(INPUTFILE));
			transformer.transform(source, result);
	        
	        return this.getRegistraties();
		} 
		catch (Exception e) {
			return e.getMessage();
		}
	}



	

	
	
}
