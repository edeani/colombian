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
import com.administracion.dao.SedesDao;
import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.entidad.Compras;
import com.administracion.entidad.FacturasCompras;
import com.administracion.entidad.Proveedor;
import com.administracion.entidad.Sedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class ComprasServiceImpl implements ComprasService {

    private JdbcTemplate jdbctemplate;

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
    private SedesDao sedesDao;
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
        String[] fila = detalleCompraDTO.getFactura().split("@");
        Date fechacompra = null;
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        if(detalleCompraDTO.getFecha()==null){
            fechacompra = new Date();
            detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
        }else if(detalleCompraDTO.getFecha().equals("")){
            fechacompra = new Date();
            detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
        }
        Long secuenciFacturaCompras = secuenciasMysqlDao.secuenceTable(ds, "facturas_compras");
        detalleCompraDTO.setIdFacturaCompra(secuenciFacturaCompras);
        comprasDao.insertarCompra(ds, detalleCompraDTO);
        for (int i = 0; i < fila.length; i++) {
            String[] datosFila = fila[i].split(",");
            comprasDao.insertarDetalleCompra(ds, i, detalleCompraDTO.getNumeroFactura(), datosFila, detalleCompraDTO.getCodigoProveedor(),fechacompra);
            actualizarPromedioInventario(nameDataSource, datosFila[0]);
        }

        //Insercion de la compra en una sede, se convierte en factura
        if (detalleCompraDTO.getIdsede() != 1L) {
            
            Sedes sede = sedesDao.findById(detalleCompraDTO.getIdsede());
            String dataSourceSede = sede.getSede();
            
            FacturaMapper facturaMapper = new FacturaMapper();
            
            DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
            Long secuenciaActual = Long.parseLong(detalleCompraDTO.getNumeroFactura());
            facturaDao.insertarFacturaSede(ds, factura, "A", secuenciaActual);
            facturaDao.insertarDetalleSede(ds, detalleCompraDTO, "A" , secuenciaActual);
        }

        ComprasMapper comprasMapper = new ComprasMapper();
        FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
        facturasComprasDao.guardarFacturaComprasDao(ds, facturasCompras);
        
    }
    
    public boolean actualizarPromedioInventario(String dataSource, String codigoProductoInventario) {
        //Armo el intervalo de fechas
        Date fecha = new Date();
        String anio = "" + Formatos.obtenerAnio(fecha);
        String mes = "" + Formatos.obtenerMes(fecha);
        String dia = "" + Formatos.obtenerDia(fecha);
        
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
            
            System.out.println("ERROR::"+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotales(String nameDataSource,String fechaInicial,String fechaFinal,String estadoCompra) {
       return comprasDao.comprasTotales(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra);
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotalesProveedor(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long codigoProveedor) {
        return comprasDao.comprasTotalesProveedor(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra,codigoProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasTotalesDTO> getDetalleCompraDTO(String nameDataSource, Long idcompra) {
        return comprasDao.getDetalleCompraDTO(idcompra, connectsAuth.getDataSourceSubSede(nameDataSource));
    } 

    @Override
    @Transactional
    public DetalleCompraDTO getCompraDTO(String nameDataSource, Long idcompra) {
        Compras compras = comprasDao.getCompra(idcompra, connectsAuth.getDataSourceSubSede(nameDataSource));
        ComprasMapper comprasMapper = new ComprasMapper();
        DetalleCompraDTO detalleCompraDTO = comprasMapper.comprasToDetalleCompraDto(compras);
        
        return detalleCompraDTO;
    }

    @Override
    @Transactional
    public Proveedor getProveedor(String nameDataSource, Long idproveedor) {
        return proveedoreDao.getProveedor(connectsAuth.getDataSourceSubSede(nameDataSource), idproveedor);
    }

    @Override
    @Transactional
    public void actualizarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        String[] fila = detalleCompraDTO.getFactura().split("@");
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        this.jdbctemplate = new JdbcTemplate(ds);
        Long idcompra = Long.parseLong(detalleCompraDTO.getNumeroFactura());
        Compras compra = comprasDao.getCompra(idcompra, ds);
        Long idFacturaCompra = compra.getIdFacturaCompra();
        Double canceladoFactura = compra.getValorTotal() - compra.getSaldo();
        comprasDao.borrarCompra(idcompra, ds);
        comprasDao.borrarDetalleCompra(idcompra, ds);
        
        //Nuevo Saldo
        detalleCompraDTO.setSaldo(Double.parseDouble(detalleCompraDTO.getTotalFactura()) - canceladoFactura);
        detalleCompraDTO.setIdFacturaCompra(idFacturaCompra);
        comprasDao.insertarCompra(ds, detalleCompraDTO);
        
        String filasCompra [] = detalleCompraDTO.getFactura().split("@");
        for (int i = 0; i < filasCompra.length; i++) {
            String datosFilaCompra[]  = filasCompra[i].split(",");
            
            comprasDao.insertarDetalleCompra(ds, i,idcompra.toString(), datosFilaCompra, compra.getCodigoProveedor().toString(),Formatos.StringDateToDate(detalleCompraDTO.getFecha()));
        }
        
        ComprasMapper comprasMapper = new ComprasMapper();
        FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
        facturasComprasDao.actualizarFacturaComprasDao(ds, facturasCompras);
        
        //Inserción en la sede escogida
        if (detalleCompraDTO.getIdsede() != 1L) {
            
            Sedes sede = sedesDao.findById(detalleCompraDTO.getIdsede());
            String dataSourceSede = sede.getSede();
            
            /**
             * Se toma el id de compra para buscar en factura
             */
            facturaDao.borrarFactura(ds, idcompra);
            facturaDao.borrarDetalleFactura(ds, idcompra);
            
            FacturaMapper facturaMapper = new FacturaMapper();
            
            DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
            facturaDao.insertarFacturaSede(ds, factura, "A", idcompra);
            facturaDao.insertarDetalleSede(ds, detalleCompraDTO, "A" , idcompra);
            
        }
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesXProveedor(connectsAuth.getDataSourceSubSede(nameDatasource), idproveedor, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesProveedores(connectsAuth.getDataSourceSubSede(nameDatasource), fechaInicio, fechaFin);
    }

    @Override
    @Transactional
    public void actualizarFactura(String nameDataSource, Compras compras) {
        comprasDao.actualizarCompra(connectsAuth.getDataSourceSubSede(nameDataSource), compras);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compras> comprasAVencer(String nameDataSource,int numeroDias,Long idProveedor) {
        return comprasDao.comprasAVencer(connectsAuth.getDataSourceSubSede(nameDataSource), numeroDias, idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal) {
        return reportesDao.reporteComprasProveedorFechaDto(connectsAuth.getDataSourceSubSede(nameDataSource), fechInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechaInicial, String fechaFinal, Long idProveedor) {
        return reportesDao.reporteCuentasPagarProveedoresDto(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal, idProveedor);
    }
}
