/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.util.Conexion;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class JasperServiceImpl implements JasperService{

    /**
     * Ejecuta el reporte jasper especificado, volcándolo como archivo alServletOutputStream del response.
     *
     * @param type tipo de exportación; puede ser pdf, xls, html, csv
     * @param jasperFile nombre del jasper para el report; por ejemplo"/ireports/myjasper.jasper"
     * @param parameters parámetros para el jasper
     * @param list java bean que contiene los datos
     * @param exportFile nombre del archivo resultado de la exportación
     * @param request
     * @param response
     * @throws JRException
     * @throws IOException
     */
    
    @Override
    @Transactional
    public void exportReport(String type, String jasperFile, Map<String, Object> parameters, Collection<?> list, String exportFile, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
        type = ( type == null ) ? "html" : type;
        exportFile = ( exportFile == null ) ? "SinNombre" : exportFile;
        if ( jasperFile == null ) { return; }

        JRDataSource dataSource = new JRBeanCollectionDataSource(list);
        Conexion conexion =  new Conexion();
        conexion.establecerConexion();
        
        String jasperFilename = request.getSession().getServletContext().getRealPath(jasperFile);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilename, parameters, conexion.getConexion());
        ServletOutputStream outputStream = response.getOutputStream();

        JRExporter exporter;

        if ( type.equals("pdf") ) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition",
                "attachment; filename=" + exportFile + ".pdf");
            exporter = new JRPdfExporter();
        } else if (type.equals("xls")) {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                "attachment; filename=" + exportFile + ".xls");
            exporter = new JRXlsExporter();
            //exporter = new JExcelApiExporter();
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
        } else if (type.equals("csv")) {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition","attachment; filename=" + exportFile + ".csv");
            exporter = new JRCsvExporter();
        } else { //if (type.equals("html"))
            response.setContentType("text/html");
            response.addHeader("Content-Disposition",
                "attachment; filename=" + exportFile + ".html");
            exporter = new JRHtmlExporter();

            Map<String,String> imagesMap = new HashMap<String,String>();
            request.getSession().setAttribute("IMAGES_MAP", imagesMap);
            // exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP,imagesMap);
            // exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?image=");
        }

        exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
        exporter.exportReport();

        conexion.destruir();
        outputStream.flush();
        outputStream.close();
    }
    
}
