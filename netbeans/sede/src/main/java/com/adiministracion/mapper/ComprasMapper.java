/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;


import com.administracion.dto.ComprasDto;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.entidad.Compras;
import com.administracion.entidad.FacturasCompras;
import com.administracion.util.Formatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class ComprasMapper {

    private static final String cuenta_facturas_compras = "620501";

    public DetalleCompraDTO comprasToDetalleCompraDto(Compras compra) {
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        if (compra != null) {

            detalleCompraDTO.setCodigoProveedor(compra.getCodigoProveedor().toString());
            detalleCompraDTO.setNumeroFactura(compra.getIdCompra().toString());
            detalleCompraDTO.setTotalFactura(compra.getValorTotal().toString());
            if (compra.getFechaVencimiento() != null) {
                detalleCompraDTO.setFechaVencimiento(Formatos.dateTostring(compra.getFechaVencimiento()));
            }
            detalleCompraDTO.setFecha(Formatos.dateTostring(compra.getFechaCompra()));
            detalleCompraDTO.setSaldo(compra.getSaldo());
            detalleCompraDTO.setEstadoCompraProveedor(compra.getEstadoCompraProveedor());
            if(compra.getIdsede()!=null){
                detalleCompraDTO.setIdsede(compra.getIdsede());
            }else{
                detalleCompraDTO.setIdsede(0L);
            }
        }
        return detalleCompraDTO;
    }

    private List<Compras> comprasListaDtoTOCompras(List<ComprasDto> listaComprasDto) {
        List<Compras> listaCompras = new ArrayList<>();
        if (listaComprasDto != null) {
            listaComprasDto.stream().map((comprasDto) -> comprasDtoTOCompras(comprasDto)).forEachOrdered((compra) -> {
                listaCompras.add(compra);
            });
        }
        return listaCompras;
    }

    private Compras comprasDtoTOCompras(ComprasDto comprasDto) {
        Compras compra = new Compras();
        if (comprasDto != null) {
            compra.setIdCompra(comprasDto.getIdCompra());
            compra.setFechaCompra(Formatos.StringDateToDate(comprasDto.getFechaCompra()));
            compra.setEstadoCompra(comprasDto.getEstadoCompra().charAt(0));
            compra.setValorTotal(comprasDto.getValorTotal());
            compra.setCodigoProveedor(comprasDto.getCodigoProveedor());
            compra.setEstadoCompraProveedor(comprasDto.getEstadoCompraProveedor());
            compra.setSaldo(comprasDto.getSaldo());
            compra.setFechaVencimiento(Formatos.StringDateToDate(comprasDto.getFechaVencimiento()));
            if(comprasDto.getIdSede()!=null){
		compra.setIdsede(comprasDto.getIdSede());
	    }
            if (comprasDto.getIdFacturaCompra() != null) {
                compra.setIdFacturaCompra(comprasDto.getIdFacturaCompra());
            }
        }
        return compra;
    }

    public List<ComprasDto> comprasListaTOComprasDto(List<Compras> listaCompras) {
        List<ComprasDto> listaComprasDtos = new ArrayList<>();
        if (listaCompras != null) {
            listaCompras.stream().map((compras) -> comprasTOComprasDto(compras)).forEachOrdered((comprasDto) -> {
                listaComprasDtos.add(comprasDto);
            });
        }
        return listaComprasDtos;
    }

    public ComprasDto comprasTOComprasDto(Compras compras) {

        ComprasDto comprasDto = new ComprasDto();
        if (compras != null) {
            comprasDto.setIdCompra(compras.getIdCompra());
            comprasDto.setCodigoProveedor(compras.getCodigoProveedor());
            comprasDto.setEstadoCompra("" + compras.getEstadoCompra());
            comprasDto.setFechaCompra(Formatos.dateTostring(compras.getFechaCompra()));
            comprasDto.setSaldo(compras.getSaldo());
            comprasDto.setEstadoCompraProveedor(compras.getEstadoCompraProveedor());
            comprasDto.setValorTotal(compras.getValorTotal());
            comprasDto.setFechaVencimiento(Formatos.dateTostring(compras.getFechaVencimiento()));
            comprasDto.setIdSede(compras.getIdsede());
            if (compras.getIdFacturaCompra() != null) {
                comprasDto.setIdFacturaCompra(compras.getIdFacturaCompra());
            }
        }
        return comprasDto;
    }

    public FacturasCompras detalleCompraDTOToFacturasComprasDto(DetalleCompraDTO detalleCompraDTO) {
        FacturasCompras facturasCompras = new FacturasCompras();
        facturasCompras.setConsecutivo(detalleCompraDTO.getIdFacturaCompra());
        facturasCompras.setFecha(Formatos.StringDateToDate(detalleCompraDTO.getFecha()));
        facturasCompras.setReferencia(Long.parseLong(detalleCompraDTO.getNumeroFactura()));
        facturasCompras.setIdsede(detalleCompraDTO.getIdsede());
        facturasCompras.setTotal(Double.parseDouble(detalleCompraDTO.getTotalFactura()));
        facturasCompras.setIdcuenta(cuenta_facturas_compras);

        return facturasCompras;
    }

    public FacturasCompras comprasToFacturasComprasDto(Compras compras) {
        FacturasCompras facturasCompras = new FacturasCompras();

        facturasCompras.setFecha(compras.getFechaCompra());
        facturasCompras.setReferencia(compras.getIdCompra());
        facturasCompras.setIdsede(compras.getIdsede());
        facturasCompras.setTotal(compras.getValorTotal());
        facturasCompras.setIdcuenta(cuenta_facturas_compras);

        return facturasCompras;
    }
}
