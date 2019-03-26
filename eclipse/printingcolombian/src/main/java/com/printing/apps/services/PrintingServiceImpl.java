package com.printing.apps.services;

import java.io.InputStream;
import java.util.Date;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.printing.apps.components.LocalConfigurationPrinting;
import com.printing.apps.dao.OrdenesDao;
import com.printing.apps.dao.PrintingDao;
import com.printing.apps.dto.DataOrden;
import com.printing.apps.dto.ItemFacturaDto;
import com.printing.apps.entity.Printing;
import com.printing.apps.enumeration.StateEnum;
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

@Component
public class PrintingServiceImpl implements PrintingService {

	@Autowired
	private LocalConfigurationPrinting localConfigurationPrinting;

	@Autowired
	private PrintingDao printingDao;

	@Autowired
	private OrdenesDao ordenesDao;

	//TO DO: descomentar antes de colocar en producción
	//@Scheduled(cron = "0/20 * * * * *")
	@Override
	public synchronized void printOrdersPending() {

		List<Printing> printings = printingDao.findPrintingState(StateEnum.Pendiente.getEstado());

		printings.forEach((print) -> {
			printOrderPendingById(print.getOrden());
		});
	}

	
	@Override
	public synchronized void printOrderPendingById(Long idOrder) {

		printingDao.updatePrintingState(StateEnum.Pendiente.getEstado(), StateEnum.Progreso.getEstado(), idOrder);
		
		DataOrden dataOrden = ordenesDao.getDataOrder(idOrder);
		
		List<ItemFacturaDto> products = ordenesDao.findBill(idOrder);

		JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products);

		InputStream input = this.getClass().getResourceAsStream("/jasper/factura.jrxml");

		try {
			JasperDesign design = JRXmlLoader.load(input);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("encabezado", localConfigurationPrinting.getEncabezado());
			parametros.put("representante", localConfigurationPrinting.getRepresentante_domicilio());
			parametros.put("nit", localConfigurationPrinting.getNit());
			parametros.put("telefonos", localConfigurationPrinting.getTelefono());
			
			parametros.put("cliente", dataOrden.getDescripcion_cliente());
			parametros.put("direccionCliente", dataOrden.getDireccion_cliente());
			parametros.put("barrioCliente", dataOrden.getBarrio());
			parametros.put("telefonoCliente", dataOrden.getNumero_telefono());
			parametros.put("orden", dataOrden.getNumero_orden());
			parametros.put("observacion", dataOrden.getObservacion());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanCollectionDataSource);

			PrintService selectedService = PrintUtil
					.findPrintService(localConfigurationPrinting.getLocal_printer_name());

			PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			// printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
			printRequestAttributeSet.add(new Copies(1));

			PrinterName printerName = new PrinterName(selectedService.getName(), null); // gets printer

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

			Printing printing = printingDao.findPrintingByIdState(idOrder, StateEnum.Progreso.getEstado());
			printing.setFechaImpresion(new Date());
			printing.setEstado(StateEnum.Procesada.getEstado());
			printingDao.updatePrinting(printing);
		} catch (Exception e) {
			Logger.getLogger(PrintingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}

	}


	@Override
	public synchronized Printing savePrintOrder(Long idOrder) {
		Printing p = null;

		p = this.printingDao.findPrintingByIdState(idOrder, StateEnum.Pendiente.getEstado());
		p = p == null ? this.printingDao.findPrintingByIdState(idOrder, StateEnum.Progreso.getEstado()) : p;

		if (p == null) {
			Printing printing = new Printing();

			printing.setOrden(idOrder);
			printing.setEstado(StateEnum.Pendiente.getEstado());
			printing.setFechaCreacion(new Date());

			this.printingDao.savePrinting(printing);
		}

		return p;
	}

}
