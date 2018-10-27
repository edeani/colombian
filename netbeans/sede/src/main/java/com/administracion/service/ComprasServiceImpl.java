/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.adiministracion.mapper.ComprasMapper;
import com.adiministracion.mapper.FacturaMapper;
import com.administracion.dao.ComprasDao;
import com.administracion.dao.FacturaDao;
import com.administracion.dao.FacturasComprasDao;
import com.administracion.dao.ProveedoresDao;
import com.administracion.dao.ReportesDao;
import com.administracion.dao.SecuenciasMysqlDao;
import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Compras;
import com.administracion.entidad.FacturasCompras;
import com.administracion.entidad.Proveedor;
import com.administracion.enumeration.TipoSedeEnum;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class ComprasServiceImpl implements ComprasService {


    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private ComprasDao comprasDao;
    @Autowired
    private ProveedoresDao proveedoreDao;
    @Autowired
    private FacturasComprasDao facturasComprasDao;
    @Autowired
    private SecuenciasMysqlDao secuenciasMysqlDao;
    @Autowired
    private ReportesDao reportesDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private ConnectsAuth connectsAuth;

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> listaProveedores(String nameDataSource) {
        return proveedoreDao.proveedoresItems(connectsAuth.getDataSourceSede(nameDataSource));
    }

    @Override
    @Transactional
    public void guardarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        /**
         * Subsede y seded de la base de datos de credencials
         */
        SubSedesDto subSede = connectsAuth.findSubsedeXId(detalleCompraDTO.getIdsede().intValue());
        SedesDto sedesDto = connectsAuth.findSedeXName(nameDataSource);
        if ((subSede.getSede().contains("Principal") && sedesDto.getTipo_sede() == TipoSedeEnum.PRINCIPAL.getTipo_sede())
                || (!subSede.getSede().contains("Principal"))) {
            String[] fila = detalleCompraDTO.getFactura().split("@");
            Date fechacompra = null;
            DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
            if (detalleCompraDTO.getFecha() == null) {
                fechacompra = new Date();
                detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
            } else if (detalleCompraDTO.getFecha().equals("")) {
                fechacompra = new Date();
                detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
            }
            Long secuenciFacturaCompras = secuenciasMysqlDao.secuenceTable(ds, "facturas_compras");
            detalleCompraDTO.setIdFacturaCompra(secuenciFacturaCompras);
            if (sedesDto.getTipo_sede() == TipoSedeEnum.NORMAL.getTipo_sede()) {
                comprasDao.insertarCompra(ds, detalleCompraDTO);
            } else {
                comprasDao.insertarCompraSede(ds, detalleCompraDTO);
            }
            for (int i = 0; i < fila.length; i++) {
                String[] datosFila = fila[i].split(",");
                comprasDao.insertarDetalleCompra(ds, i, detalleCompraDTO.getNumeroFactura(), datosFila, detalleCompraDTO.getCodigoProveedor(), fechacompra);
                actualizarPromedioInventario(nameDataSource, datosFila[0]);
            }

            //Insercion de la compra en una sede, se convierte en factura
            //El tipo de sede 1 es una sede normal.
            if (sedesDto.getTipo_sede() == TipoSedeEnum.NORMAL.getTipo_sede()) {

                DataSource dsSubsede = connectsAuth.getDataSourceSubSede(subSede.getSede());
                FacturaMapper facturaMapper = new FacturaMapper();

                DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
                Long secuenciaActual = Long.parseLong(detalleCompraDTO.getNumeroFactura());
                facturaDao.insertarFacturaSubSede(dsSubsede, factura, "A", secuenciaActual);
                facturaDao.insertarDetalleSede(dsSubsede, detalleCompraDTO, "A", secuenciaActual);
            }

            ComprasMapper comprasMapper = new ComprasMapper();
            FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
            facturasComprasDao.guardarFacturaComprasDao(ds, facturasCompras);
        }
    }

    public boolean actualizarPromedioInventario(String dataSource, String codigoProductoInventario) {
        //Armo el intervalo de fechas
        //Date fecha = new Date();
        //String anio = "" + Formatos.obtenerAnio(fecha);
        //String mes = "" + Formatos.obtenerMes(fecha);
        //String dia = "" + Formatos.obtenerDia(fecha);

        Date date = Formatos.lunesSemana();
        String fechainicial = Formatos.dateTostring(date);
        String fechafinal = Formatos.sumarFechasDias(date, 6);
        //String fechainicial = anio + "-" + mes + "-01";
        //String fechafinal = anio + "-" + mes + "-" + dia;
        try {

            //Calculo el promedio
            Double promedio = inventarioService.calcularPromedioInventario(dataSource, Long.parseLong(codigoProductoInventario), fechainicial, fechafinal);
            /*if(Long.parseLong(codigoProductoInventario) == 1 ){
                promedio += 275f;
            }else if(Long.parseLong(codigoProductoInventario) == 2 ){
                promedio += 250f;
            }*/
            //Le doy un formato al  promedio
            promedio = Double.parseDouble(Formatos.formatearNumeros("####.###", promedio));
            //Actualizo el promedio en inventario
            inventarioService.actualizarPromedioInventario(dataSource, Long.parseLong(codigoProductoInventario), promedio);

        } catch (NumberFormatException e) {

            System.out.println("ERROR::" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotales(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra) {
        return comprasDao.comprasTotales(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra);
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotalesProveedor(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long codigoProveedor) {
        return comprasDao.comprasTotalesProveedor(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra, codigoProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasTotalesDTO> getDetalleCompraDTO(String nameDataSource, Long idcompra,Integer codigProveedor) {
        return comprasDao.getDetalleCompraDTO(idcompra,codigProveedor, connectsAuth.getDataSourceSede(nameDataSource));
    }

    @Override
    @Transactional
    public DetalleCompraDTO getCompraDTO(String nameDataSource, Long idcompra,Integer codigoProveedor) {
        System.out.println("getCompraDTO:: "+nameDataSource+" "+idcompra);
        Compras compras = comprasDao.getCompraXProveedor(connectsAuth.getDataSourceSede(nameDataSource),
                idcompra, codigoProveedor);
        System.out.println("getCompraDTO::compras "+compras);
        ComprasMapper comprasMapper = new ComprasMapper();
        DetalleCompraDTO detalleCompraDTO = comprasMapper.comprasToDetalleCompraDto(compras);
        if (detalleCompraDTO.getIdsedepoint() != null) {
            if (detalleCompraDTO.getIdsedepoint() != 0) {
                detalleCompraDTO.setIdsede(connectsAuth.getIdSubSedePrincpipal(nameDataSource,
                        detalleCompraDTO.getIdsedepoint()).longValue());
            }
        }

        return detalleCompraDTO;

    }

    @Override
    @Transactional
    public Proveedor getProveedor(String nameDataSource, Long idproveedor) {
        return proveedoreDao.getProveedor(connectsAuth.getDataSourceSede(nameDataSource), idproveedor);
    }

    @Override
    @Transactional
    public void actualizarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        SubSedesDto subSede = connectsAuth.findSubsedeXId(detalleCompraDTO.getIdsede().intValue());
        SedesDto sedesDto = connectsAuth.findSedeXName(nameDataSource);
        if ((subSede.getSede().contains("Principal") && Objects.equals(sedesDto.getTipo_sede(), TipoSedeEnum.PRINCIPAL.getTipo_sede()))
                || !subSede.getSede().contains("Principal")) {
            String[] fila = detalleCompraDTO.getFactura().split("@");
            DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
            Long idcompra = Long.parseLong(detalleCompraDTO.getNumeroFactura());
            Integer codigoProveedor=Integer.valueOf(detalleCompraDTO.getCodigoProveedor());
            Compras compra = comprasDao.getCompraXProveedor(ds,idcompra, codigoProveedor);
            Long idFacturaCompra = compra.getIdFacturaCompra();
            Double canceladoFactura = compra.getValorTotal() - compra.getSaldo();
            comprasDao.borrarCompra(idcompra,codigoProveedor, ds);
            comprasDao.borrarDetalleCompra(idcompra,codigoProveedor, ds);

            //Nuevo Saldo
            detalleCompraDTO.setSaldo(Double.parseDouble(detalleCompraDTO.getTotalFactura()) - canceladoFactura);
            detalleCompraDTO.setIdFacturaCompra(idFacturaCompra);
            if (Objects.equals(sedesDto.getTipo_sede(), TipoSedeEnum.NORMAL.getTipo_sede())) {
                comprasDao.insertarCompra(ds, detalleCompraDTO);
            } else {
                comprasDao.insertarCompraSede(ds, detalleCompraDTO);
            }

            String filasCompra[] = detalleCompraDTO.getFactura().split("@");
            for (int i = 0; i < filasCompra.length; i++) {
                String datosFilaCompra[] = filasCompra[i].split(",");

                comprasDao.insertarDetalleCompra(ds, i, idcompra.toString(), datosFilaCompra, compra.getCodigoProveedor().toString(), Formatos.StringDateToDate(detalleCompraDTO.getFecha()));
            }

            ComprasMapper comprasMapper = new ComprasMapper();
            FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
            facturasComprasDao.actualizarFacturaComprasDao(ds, facturasCompras);

            //Inserción en la sede escogida
            if (Objects.equals(sedesDto.getTipo_sede(), TipoSedeEnum.NORMAL.getTipo_sede())) {
                DataSource dsSubSede = connectsAuth.getDataSourceSubSede(subSede.getSede());
                /**
                 * Se toma el id de compra para buscar en factura
                 */
                facturaDao.borrarFactura(dsSubSede, idcompra);
                facturaDao.borrarDetalleFactura(dsSubSede, idcompra);

                FacturaMapper facturaMapper = new FacturaMapper();

                DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
                facturaDao.insertarFacturaSubSede(dsSubSede, factura, "A", idcompra);
                facturaDao.insertarDetalleSede(dsSubSede, detalleCompraDTO, "A", idcompra);

            }
        }
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesXProveedor(connectsAuth.getDataSourceSede(nameDatasource), idproveedor, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesProveedores(connectsAuth.getDataSourceSede(nameDatasource), fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compras> comprasAVencer(String nameDataSource, int numeroDias, Long idProveedor) {
        return comprasDao.comprasAVencer(connectsAuth.getDataSourceSede(nameDataSource), numeroDias, idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal) {
        return reportesDao.reporteComprasProveedorFechaDto(connectsAuth.getDataSourceSede(nameDataSource), fechInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechaInicial, String fechaFinal, Long idProveedor) {
        return reportesDao.reporteCuentasPagarProveedoresDto(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal, idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Compras getCompraXIDproveedor(String nameDataSource, Long idCompra, Integer codigopProveedor) {
        return comprasDao.getCompraXProveedor(connectsAuth.getDataSourceSede(nameDataSource), idCompra, codigopProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public void actualizarCompraCabecera(String nameDataSource, Long consecutivo) {
        DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
        
        Compras compra = comprasDao.getCompraXConsecutivo(consecutivo, ds);
        compra.setSaldo(Double.NaN);
        comprasDao.actualizarCompraXConsecutivo(ds, compra);
    }

    @Override
    @Transactional
    public void actualizarSaldosCompra(String dataSource, List<DetallePagosProveedorDto> detallePagosProveedor, Integer codigoProveedor) {
        
        detallePagosProveedor.forEach((pago) -> {
            comprasDao.actualizarSaldosCompra(connectsAuth.getDataSourceSede(dataSource), pago, codigoProveedor);
        });
        
    }
}
