/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.ComprasDao;
import com.colombia.cali.colombiancaliycali.dao.FacturaDao;
import com.colombia.cali.colombiancaliycali.dao.FacturasComprasDao;
import com.colombia.cali.colombiancaliycali.dao.ProveedoresDao;
import com.colombia.cali.colombiancaliycali.dao.ReportesDao;
import com.colombia.cali.colombiancaliycali.dao.SecuenciasMysqlDao;
import com.colombia.cali.colombiancaliycali.dao.SedesDao;
import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.ComprasProveedorFechaDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasTotalesDTO;
import com.colombian.cali.colombiancaliycali.dto.CuentasPagarProveedoresDto;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import com.colombian.cali.colombiancaliycali.entidades.Factura;
import com.colombian.cali.colombiancaliycali.entidades.FacturasCompras;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.mapper.ComprasMapper;
import com.colombian.cali.colombiancaliycali.mapper.FacturaMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
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

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> listaProveedores(String nameDataSource) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        return jdbctemplate.query("select idproveedor as id,nombre as label from proveedor order by 2", new BeanPropertyRowMapper(ItemsDTO.class));
    }

    @Override
    @Transactional
    public void guardarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        String[] fila = detalleCompraDTO.getFactura().split("@");
        Date fechacompra = null;
        
        if(detalleCompraDTO.getFecha()==null){
            fechacompra = new Date();
            detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
        }else if(detalleCompraDTO.getFecha().equals("")){
            fechacompra = new Date();
            detalleCompraDTO.setFecha(Formatos.dateTostring(fechacompra));
        }
        Long secuenciFacturaCompras = secuenciasMysqlDao.secuenceTable(nameDataSource, "facturas_compras");
        detalleCompraDTO.setIdFacturaCompra(secuenciFacturaCompras);
        comprasDao.insertarCompra(nameDataSource, detalleCompraDTO);
        for (int i = 0; i < fila.length; i++) {
            String[] datosFila = fila[i].split(",");
            comprasDao.insertarDetalleCompra(nameDataSource, i, detalleCompraDTO.getNumeroFactura(), datosFila, detalleCompraDTO.getCodigoProveedor(),fechacompra);
            actualizarPromedioInventario(nameDataSource, datosFila[0]);
        }

        //Insercion de la compra en una sede, se convierte en factura
        if (detalleCompraDTO.getIdsede() != 1L) {
            
            Sedes sede = sedesDao.findSede(detalleCompraDTO.getIdsede());
            String dataSourceSede = sede.getSede();
            
            FacturaMapper facturaMapper = new FacturaMapper();
            
            DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
            Long secuenciaActual = Long.parseLong(detalleCompraDTO.getNumeroFactura());
            facturaDao.insertarFacturaSede(dataSourceSede, factura, "A", secuenciaActual);
            facturaDao.insertarDetalleSede(dataSourceSede, detalleCompraDTO, "A" , secuenciaActual);
        }

        ComprasMapper comprasMapper = new ComprasMapper();
        FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
        facturasComprasDao.guardarFacturaComprasDao(nameDataSource, facturasCompras);
        
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
            
        } catch (Exception e) {
            
            System.out.println("ERROR::"+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotales(String nameDataSource,String fechaInicial,String fechaFinal,String estadoCompra) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprasTotalesDTO> reporte = null;
        String sql = " select subCompras3.*,(subCompras3.valor/subCompras3.unidades) as promedio from (select subCompras2.codigo,subCompras2.producto, sum(subCompras2.unidades) as unidades, "+
                     " sum(subCompras2.valor) as valor "+
                     " from ( select subCompras.codigo as codigo,subCompras.producto as producto, case when subCompras.unidades is null then 0 else subCompras.unidades end as unidades, " +
                     " case when subCompras.estado_compra is null then '"+estadoCompra+"' else subCompras.estado_compra end as estado_compra, " +
                     " case when subCompras.fecha_compra is null or subCompras.fecha_compra = '' then '"+fechaInicial+"' else subCompras.fecha_compra end as fecha_compra  , " +
                     " case when subCompras.promedio is null then 0 else subCompras.promedio end as promedio, " +
                     " case when subCompras.valor is null then 0 else subCompras.valor end as valor "+
                     " from (select i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "+
                     " dc.numero_unidades as unidades,i.promedio as promedio,dc.valor_producto as valor,c.estado_compra ,dc.fecha_compra "+
                     " from inventario i inner join detalle_compra dc on i.codigo_producto_inventario = dc.codigo_producto_inventario " +
                     " inner join compras c on dc.numero_compra = c.id_compra order by 1)subCompras "+
                     " )subCompras2 where subCompras2.estado_compra = '"+estadoCompra+"' and subCompras2.fecha_compra between '"+fechaInicial+"' and '"+fechaFinal+"' "+
                     " group by subCompras2.codigo,subCompras2.producto) subCompras3 order by 1";
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper(ComprasTotalesDTO.class));
        } catch (Exception e) {
            System.out.println("Excepción: "+e.getMessage());
        }
        
        return reporte;
    }

    @Override
    @Transactional
    public List<ComprasTotalesDTO> comprasTotalesProveedor(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long codigoProveedor) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprasTotalesDTO> reporte = null;
        String sql = "select subCompras3.codigo,subCompras3.producto, subCompras3.unidades, case when subCompras3.unidades = 0 or subCompras3.unidades is null then 0 else subCompras3.valor/subCompras3.unidades end as promedio, subCompras3.valor as valor"+
                     " from ( select subCompras2.codigo,subCompras2.producto, sum(subCompras2.unidades) as unidades, "+
                     " sum(subCompras2.valor) as valor,subCompras2.proveedor "+
                     " from ( select subCompras.codigo as codigo,subCompras.producto as producto,  " +
                     " case when subCompras.unidades is null then 0 else subCompras.unidades end as unidades, " +
                     " case when subCompras.estado_compra is null then '"+estadoCompra+"' else subCompras.estado_compra end as estado_compra, "+
                     " case when subCompras.fecha_compra is null or subCompras.fecha_compra = '' then '"+fechaInicial+"' else subCompras.fecha_compra end as fecha_compra  ,"+
                     " case when subCompras.valor is null or subCompras.proveedor is null then 0 else subCompras.valor end as valor, " +
                     " subCompras.proveedor from ( "+
                     " select i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "+
                     " dc.numero_unidades as unidades,dc.valor_producto as valor,c.estado_compra ,dc.fecha_compra,c.codigo_proveedor as proveedor "+
                     " from inventario i inner join detalle_compra dc on i.codigo_producto_inventario = dc.codigo_producto_inventario "+
                     " inner join compras c on dc.numero_compra = c.id_compra and c.codigo_proveedor = "+codigoProveedor +
                     " order by 1)subCompras )subCompras2 "+
                     " where subCompras2.estado_compra = '"+estadoCompra+"' and subCompras2.fecha_compra between '"+fechaInicial+"' and '"+fechaFinal+"' "+
                     " group by subCompras2.codigo,subCompras2.producto )subCompras3 order by 1";
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper(ComprasTotalesDTO.class));
        } catch (Exception e) {
            System.out.println("comprasTotalesProveedor:: "+e.getMessage());
        }
            
        return reporte;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasTotalesDTO> getDetalleCompraDTO(String nameDataSource, Long idcompra) {
        return comprasDao.getDetalleCompraDTO(idcompra, nameDataSource);
    } 

    @Override
    @Transactional
    public DetalleCompraDTO getCompraDTO(String nameDataSource, Long idcompra) {
        Compras compras = comprasDao.getCompra(idcompra, nameDataSource);
        ComprasMapper comprasMapper = new ComprasMapper();
        DetalleCompraDTO detalleCompraDTO = comprasMapper.comprasToDetalleCompraDto(compras);
        
        return detalleCompraDTO;
    }

    @Override
    @Transactional
    public Proveedor getProveedor(String nameDataSource, Long idproveedor) {
        return proveedoreDao.getProveedor(nameDataSource, idproveedor);
    }

    @Override
    @Transactional
    public void actualizarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        String[] fila = detalleCompraDTO.getFactura().split("@");
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        Long idcompra = Long.parseLong(detalleCompraDTO.getNumeroFactura());
        Compras compra = comprasDao.getCompra(idcompra, nameDataSource);
        Long idFacturaCompra = compra.getIdFacturaCompra();
        Double canceladoFactura = compra.getValorTotal() - compra.getSaldo();
        comprasDao.borrarCompra(idcompra, nameDataSource);
        comprasDao.borrarDetalleCompra(idcompra, nameDataSource);
        
        //Nuevo Saldo
        detalleCompraDTO.setSaldo(Double.parseDouble(detalleCompraDTO.getTotalFactura()) - canceladoFactura);
        detalleCompraDTO.setIdFacturaCompra(idFacturaCompra);
        comprasDao.insertarCompra(nameDataSource, detalleCompraDTO);
        
        String filasCompra [] = detalleCompraDTO.getFactura().split("@");
        for (int i = 0; i < filasCompra.length; i++) {
            String datosFilaCompra[]  = filasCompra[i].split(",");
            
            comprasDao.insertarDetalleCompra(nameDataSource, i,idcompra.toString(), datosFilaCompra, compra.getCodigoProveedor().toString(),Formatos.StringDateToDate(detalleCompraDTO.getFecha()));
        }
        
        ComprasMapper comprasMapper = new ComprasMapper();
        FacturasCompras facturasCompras = comprasMapper.detalleCompraDTOToFacturasComprasDto(detalleCompraDTO);
        facturasComprasDao.actualizarFacturaComprasDao(nameDataSource, facturasCompras);
        
        //Inserción en la sede escogida
        if (detalleCompraDTO.getIdsede() != 1L) {
            
            Sedes sede = sedesDao.findSede(detalleCompraDTO.getIdsede());
            String dataSourceSede = sede.getSede();
            
            /**
             * Se toma el id de compra para buscar en factura
             */
            facturaDao.borrarFactura(dataSourceSede, idcompra);
            facturaDao.borrarDetalleFactura(dataSourceSede, idcompra);
            
            FacturaMapper facturaMapper = new FacturaMapper();
            
            DetalleFacturaDTO factura = facturaMapper.comprasToFactura(detalleCompraDTO);
            facturaDao.insertarFacturaSede(dataSourceSede, factura, "A", idcompra);
            facturaDao.insertarDetalleSede(dataSourceSede, detalleCompraDTO, "A" , idcompra);
            
        }
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesXProveedor(nameDatasource, idproveedor, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio, String fechaFin) {
        return comprasDao.comprasTotalesProveedores(nameDatasource, fechaInicio, fechaFin);
    }

    @Override
    @Transactional
    public void actualizarFactura(String nameDataSource, Compras compras) {
        comprasDao.actualizarCompra(nameDataSource, compras);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compras> comprasAVencer(String nameDataSource,int numeroDias,Long idProveedor) {
        return comprasDao.comprasAVencer(nameDataSource, numeroDias, idProveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal) {
        return reportesDao.reporteComprasProveedorFechaDto(nameDataSource, fechInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechaInicial, String fechaFinal, Long idProveedor) {
        return reportesDao.reporteCuentasPagarProveedoresDto(nameDataSource, fechaInicial, fechaFinal, idProveedor);
    }
}
