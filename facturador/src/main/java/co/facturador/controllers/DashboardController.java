/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers;

import co.facturador.controllers.util.PrintUtil;
import co.facturador.dto.ItemFactura;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author EderArmando
 */
public class DashboardController implements Initializable {

    @FXML
    private Pane contentDashBoard;
    @FXML
    private Label lSeccion;
    
    @FXML
    public void cargarFacturar(ActionEvent event){
        FacturarController fc = new FacturarController();
        lSeccion.setText("Facturador");
        fc.crearFactura(contentDashBoard);
    }
    
    @FXML
    public void imprimirFactura(ActionEvent event) throws JRException, PrinterException{
        List<ItemFactura> detalleFactura = new ArrayList<ItemFactura>();
        ItemFactura item = new ItemFactura();
            item.setCodigoProducto(1);
            item.setDescripcion("product 1");
            item.setUnidades(5);
            item.setValorUnitario(63F);
            item.setValorTotal(485f);
            
            ItemFactura item1 = new ItemFactura();
            item1.setCodigoProducto(1);
            item1.setDescripcion("product 3");
            item1.setUnidades(5);
            item1.setValorUnitario(63F);
            item1.setValorTotal(485f);
            
            detalleFactura.add(item);
            detalleFactura.add(item1);
        
            JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(detalleFactura);
            String reporteJrxml ="C:/Users/EderArmando/Documents/NetBeansProjects/git/colombian/facturador/src/main/resources/jasper/factura.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reporteJrxml);
            Map<String,Object> parametros = new  HashMap<String,Object>();
            parametros.put("usuario", "edeani");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parametros, beanCollectionDataSource);
            
            PrintService selectedService = PrintUtil.findPrintService("EPSON TM-T20 Receipt");
            
            if(selectedService != null)
                {
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
                    printerJob.setPrintService(selectedService);
                    boolean printSucceed = JasperPrintManager.printReport(jasperPrint, false);
                    if(printSucceed){
                        System.out.println("Imprimí");
                    }else{
                        System.out.println("Try again");
                    }
                }
            JasperViewer.viewReport(jasperPrint, false);
            
            
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
