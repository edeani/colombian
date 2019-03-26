package com.printing.apps.components;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
public class LeerXml {

    private static final String FOLDER_CONSULTAS = "static/sql/";
    public  String getQuery(String nameQuery) {
        
        try {
            String nombreArchivo = nameQuery.split("\\.")[0];
            ClassPathResource resource = new ClassPathResource(FOLDER_CONSULTAS.concat(nombreArchivo).concat(".xml"));
 
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(resource.getInputStream());
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
