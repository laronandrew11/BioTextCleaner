import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class FileHandler {

	public static Document importFile(String filename)
	{
		File inputFile = new File(filename);
		
		DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
				try {
					 builder= factory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Document doc=null;
				try {
					doc = builder.parse(inputFile);
					 doc.getDocumentElement().normalize();
				} catch (SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return doc;
		        
	}
	public static void exportFile()
	{
		//File OutputFile=new File("output.xml");
		
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder=null;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         Document doc = dBuilder.newDocument();
		         
		         Element rootElement = doc.createElement("article");
		         doc.appendChild(rootElement);
		         
		         
		         /**export document to file**/
		         TransformerFactory transformerFactory =
		                 TransformerFactory.newInstance();
		                 Transformer transformer=null;
						try {
							transformer = transformerFactory.newTransformer();
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                 DOMSource source = new DOMSource(doc);
		                 StreamResult result =
		                 new StreamResult(new File("files\\output.xml"));
		                 try {
							transformer.transform(source, result);
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                 // Output to console for testing
		                 StreamResult consoleResult =
		                 new StreamResult(System.out);
		                 try {
							transformer.transform(source, consoleResult);
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	}
	public static void testOutput(Document doc)
	{
        System.out.println("Root element :" 
                + doc.getDocumentElement().getNodeName());
             NodeList nList = doc.getElementsByTagName("sec");
             System.out.println("----------------------------");
             for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" 
                   + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   System.out.println(eElement.getAttribute("sec-type"));
                   System.out.println(eElement.getTextContent());
                   
                   /*System.out.println("Student roll no : " 
                      + eElement.getAttribute("rollno"));
                   System.out.println("First Name : " 
                      + eElement
                      .getElementsByTagName("firstname")
                      .item(0)
                      .getTextContent());
                   System.out.println("Last Name : " 
                   + eElement
                      .getElementsByTagName("lastname")
                      .item(0)
                      .getTextContent());
                   System.out.println("Nick Name : " 
                   + eElement
                      .getElementsByTagName("nickname")
                      .item(0)
                      .getTextContent());
                   System.out.println("Marks : " 
                   + eElement
                      .getElementsByTagName("marks")
                      .item(0)
                      .getTextContent());*/
                }
             }
	}
}
