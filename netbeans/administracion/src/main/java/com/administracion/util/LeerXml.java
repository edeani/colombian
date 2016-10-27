/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */

@Component
public class LeerXml {

    private static final String FOLDER_CONSULTAS = "consultas/";
    private static final String EXT_XML = ".xml";
    public  String getQuery(String nameQuery) {
        
        try {
            String nombreArchivo = nameQuery.split("\\.")[0];
            
            ClassLoader classLoader = getClass().getClassLoader();
            String path= classLoader.getResource(FOLDER_CONSULTAS.concat(nombreArchivo).concat(EXT_XML)).getFile();
            path = URLDecoder.decode(path, "utf-8");
            path = new File(path).getPath();
            File fXmlFile = new File(path);
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
            System.out.println("getQuery::"+ex.getMessage());
        }
        
        
        return "";
    }

}
