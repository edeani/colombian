/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.mapper;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDaoImpl;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.entidades.FacturasCompras;
import com.colombian.cali.colombiancaliycali.entidades.FacturasProcesadasCuentas;
import java.util.Date;

/**
 *
 * @author Jose Efren
 */
public class FacturaMapper {
    private CaliycaliDao caliycaliDao;

    /**
     * Arma la inserción del detale
     * @param detalleFacturaDTO
     * @param idFactura
     * @param idsede
     * @return 
     */
    public String tramaToDetalleFactura(DetalleFacturaDTO detalleFacturaDTO,Long idFactura,Long idsede){
        String trama = detalleFacturaDTO.getFactura();
        caliycaliDao = new CaliycaliDaoImpl();
         System.out.println("FECHA6::"+detalleFacturaDTO.getFechaFactura());
        if(detalleFacturaDTO.getFechaFactura() == null){
            detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(new Date()));
        }else if(detalleFacturaDTO.getFechaFactura().equals("")){
            detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(new Date()));
        }
       
        String[] fila = trama.split("@");
        String valores = "";
        for (int i = 0; i < fila.length; i++) {
            String[] datosFila = fila[i].split(",");
            if (i == 0) {
                valores = caliycaliDao.insertJdbTemplate("secuencial_factura,numero_factura,fecha_factura,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor,idsede", "detalle_factura", (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1000," + idsede);
            } else {
                valores = caliycaliDao.addInsertJdtbTemplate(valores, (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1000," + idsede, i);
            }
        }
        
        return valores;
    }
    
    public String tramaToDetalleFacturaSede(DetalleFacturaDTO detalleFacturaDTO,Long idFactura,Long idsede){
        String trama = detalleFacturaDTO.getFactura();
        caliycaliDao = new CaliycaliDaoImpl();
        
        System.out.println("FECHA7::"+detalleFacturaDTO.getFechaFactura());
        if(detalleFacturaDTO.getFechaFactura() == null){
            detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(new Date()));
        }else if(detalleFacturaDTO.getFechaFactura().equals("")){
            detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(new Date()));
        }
        String[] fila = trama.split("@");
        String valores = "";
        for (int i = 0; i < fila.length; i++) {
            String[] datosFila = fila[i].split(",");
            if (i == 0) {
                valores = caliycaliDao.insertJdbTemplate("secuencial_factura,numero_factura,fecha_factura,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor", "detalle_factura", (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1000");
            } else {
                valores = caliycaliDao.addInsertJdtbTemplate(valores, (i + 1) + "," + idFactura + "," + "'" +detalleFacturaDTO.getFechaFactura()+ "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1000", i);
            }
        }
        
        return valores;
    }
    
    public FacturasCompras facturaToFacturaCompras(DetalleFacturaDTO detalleFacturaDTO){
        FacturasCompras facturasCompras = new FacturasCompras();
        
        facturasCompras.setFecha(Formatos.StringDateToDate(detalleFacturaDTO.getFechaFactura()));
        facturasCompras.setReferencia(Long.parseLong(detalleFacturaDTO.getNumeroFactura()));
        facturasCompras.setIdsede(detalleFacturaDTO.getSede());
        facturasCompras.setTotal(Double.parseDouble(detalleFacturaDTO.getTotalFactura()));
        
        
        return facturasCompras;
    }
    
    public FacturasProcesadasCuentas facturasComprasToFacturasProcesadasCuentas(FacturasCompras facturasCompras){
        FacturasProcesadasCuentas facturasProcesadasCuentas = new FacturasProcesadasCuentas();
        
        facturasProcesadasCuentas.setIdfactura(facturasCompras.getReferencia());
        facturasProcesadasCuentas.setIdfacturacompra(facturasCompras.getConsecutivo());
        
        return facturasProcesadasCuentas;
    }
}
