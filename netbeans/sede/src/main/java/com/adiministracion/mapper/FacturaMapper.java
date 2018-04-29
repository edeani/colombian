/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.adiministracion.mapper;

import com.administracion.dao.GenericDaoImpl;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.ItemFacturaDto;
import com.administracion.entidad.FacturasCompras;
import com.administracion.entidad.FacturasProcesadasCuentas;
import com.administracion.util.Formatos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class FacturaMapper extends GenericDaoImpl<Object>{


    /**
     * Arma la inserción del detale
     * @param detalleFacturaDTO
     * @param idFactura
     * @param idsede
     * @return 
     */
    public String tramaToDetalleFactura(DetalleFacturaDTO detalleFacturaDTO,Long idFactura,Long idsede){
        String trama = detalleFacturaDTO.getFactura();
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
                valores = insertJdbTemplate("secuencial_factura,numero_factura,fecha_factura,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor,idsede", "detalle_factura", (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1," + idsede);
            } else {
                valores = addInsertJdtbTemplate(valores, (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1," + idsede, i);
            }
        }
        
        return valores;
    }
    
    public String tramaToDetalleFacturaSede(DetalleFacturaDTO detalleFacturaDTO,Long idFactura,Long idsede){
        String trama = detalleFacturaDTO.getFactura();
        
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
                valores = insertJdbTemplate("secuencial_factura,numero_factura,fecha_factura,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor", "detalle_factura", (i + 1) + "," + idFactura + "," + "'" + detalleFacturaDTO.getFechaFactura() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1");
            } else {
                valores = addInsertJdtbTemplate(valores, (i + 1) + "," + idFactura + "," + "'" +detalleFacturaDTO.getFechaFactura()+ "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1", i);
            }
        }
        
        return valores;
    }
    
    public String tramaCompraToDetalleFacturaSede(DetalleCompraDTO detalleCompraDTO,Long idFactura,Long idsede){
        String trama = detalleCompraDTO.getFactura();
        
        System.out.println("FECHA7::"+detalleCompraDTO.getFecha());
        if(detalleCompraDTO.getFecha() == null){
            detalleCompraDTO.setFecha(Formatos.dateTostring(new Date()));
        }else if(detalleCompraDTO.getFecha().equals("")){
            detalleCompraDTO.setFecha(Formatos.dateTostring(new Date()));
        }
        String[] fila = trama.split("@");
        String valores = "";
        for (int i = 0; i < fila.length; i++) {
            String[] datosFila = fila[i].split(",");
            if (i == 0) {
                valores = insertJdbTemplate("secuencial_factura,numero_factura,fecha_factura,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor", "detalle_factura", (i + 1) + "," + idFactura + "," + "'" + detalleCompraDTO.getFecha() + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1");
            } else {
                valores = addInsertJdtbTemplate(valores, (i + 1) + "," + idFactura + "," + "'" +detalleCompraDTO.getFecha()+ "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + ",1", i);
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
    
    public DetalleFacturaDTO comprasToFactura(DetalleCompraDTO compra){
        DetalleFacturaDTO factura = new DetalleFacturaDTO();
        
        factura.setFechaFactura(compra.getFecha());
        factura.setTotalFactura(compra.getTotalFactura());
        
        return factura;
    }
    
    public static List<ItemFacturaDto> stringFacturaToIteFacturaDto(String factura){
        String[] fila = factura.split("@");
        List<ItemFacturaDto> detalleFactura = new ArrayList<>();
        for (String string : fila) {
            String[] datosFila = string.split(",");
            ItemFacturaDto item = new ItemFacturaDto();
            item.setCodigoProducto(Integer.valueOf(datosFila[0]));
            item.setDescripcion(datosFila[1]);
            item.setUnidades(Integer.valueOf(datosFila[2]));
            item.setValorTotal(Float.valueOf(datosFila[4]));
            item.setValorUnitario(Float.valueOf(datosFila[3]));
            detalleFactura.add(item);
        }
            
            return detalleFactura;
 
    }
}
