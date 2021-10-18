/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.util;

import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author EderArmando
 */
public class PrintUtil {

    public static PrintService findPrintService(String printerName) {

        printerName = printerName.toLowerCase();

        PrintService service = null;

        // Get array of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();

        // Retrieve a print service from the array
        for (int index = 0; service == null && index < services.length; index++) {

            if (services[index].getName().toLowerCase().contains(printerName)) {
                service = services[index];
            }
        }

        // Return the print service
        return service;
    }

    /**
     * Retrieves a List of Printer Service Names.
     *
     * @return List
     */
    public static List<String> getPrinterServiceNameList() {

        // get list of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();
        List<String> list = new ArrayList<String>();
        if (null != services) {
            for (PrintService service : services) {
                String nombrePrint = service.getName();
                int indexName = nombrePrint.lastIndexOf("\\");
                if(indexName!=-1){
                    nombrePrint = nombrePrint.substring(indexName+1);
                }
                list.add(nombrePrint);
            }
        }

        return list;
    }
    
    public static PrintService getDefaultPrinter(){
        return PrintServiceLookup.lookupDefaultPrintService();
    }
}
