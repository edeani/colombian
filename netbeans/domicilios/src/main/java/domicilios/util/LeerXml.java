/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */

public class LeerXml {

    private static final String FOLDER_CONSULTAS = "/consultas/";
    private static final String EXT_XML = ".xml";
    public static String getQuery(String nameQuery) {
        
        try {
            String nombreArchivo = nameQuery.split(".")[0];
            
            File fXmlFile = new File(FOLDER_CONSULTAS.concat(nombreArchivo).concat(EXT_XML));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("query");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    /**
                     * Si encuentro el elemento termina el ciclo
                     */
                    if(eElement.getAttribute("id").equals(nameQuery)){
                        return eElement.getTextContent();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("getQuery Null::"+e.getMessage());
        }catch (ParserConfigurationException | SAXException | IOException | DOMException ex){
            System.out.println("getQuery::");
        }
        
        
        return "";
    }

}
