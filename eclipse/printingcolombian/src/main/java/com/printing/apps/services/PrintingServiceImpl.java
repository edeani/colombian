package com.printing.apps.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;

import org.springframework.stereotype.Service;

import com.printing.apps.dto.ItemFacturaDto;
import com.printing.apps.util.PrintUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

@Service
public class PrintingServiceImpl implements PrintingService{

	@Override
	public void printOrder() {
		
		List<ItemFacturaDto> products   = new ArrayList<ItemFacturaDto>();
		
		ItemFacturaDto item = new ItemFacturaDto();
		item.setCodigoProducto(1);
		item.setDescripcion("Producto 1");
		item.setUnidades(1);
		item.setValorTotal(5000F);
		item.setValorUnitario(5000F);
		
		products.add(item);
		
		item = new ItemFacturaDto();
		item.setCodigoProducto(2);
		item.setDescripcion("Producto 2");
		item.setUnidades(1);
		item.setValorTotal(7000F);
		item.setValorUnitario(7000F);
		
		products.add(item);
		item = new ItemFacturaDto();
		item.setCodigoProducto(3);
		item.setDescripcion("Producto 3");
		item.setUnidades(3);
		item.setValorTotal(8000F);
		item.setValorUnitario(8000F);
		
		products.add(item);
		
		
		JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products);
		
		InputStream input = this.getClass().getResourceAsStream("/jasper/factura.jrxml");
		
		try {
			JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("usuario", "Eaanillol");
            parametros.put("proveedor", "Mi proveedor");
            parametros.put("numeroFactura", "500");
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parametros, beanCollectionDataSource);
            
            PrintService selectedService = PrintUtil.findPrintService("Local Colombian");
            
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
            printRequestAttributeSet.add(new Copies(1));

            PrinterName printerName = new PrinterName(selectedService.getName(), null); //gets printer 

            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(printerName);

            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
            
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
            configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
            configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
            configuration.setDisplayPageDialog(Boolean.FALSE);
            configuration.setDisplayPrintDialog(Boolean.FALSE);
            
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            
		} catch (Exception e) {
			Logger.getLogger(PrintingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		
	}

}
