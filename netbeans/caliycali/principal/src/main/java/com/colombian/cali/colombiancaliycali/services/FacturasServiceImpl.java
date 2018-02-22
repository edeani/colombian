/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.CambioSedeDao;
import com.colombia.cali.colombiancaliycali.dao.FacturaDao;
import com.colombia.cali.colombiancaliycali.dao.FacturasComprasDao;
import com.colombia.cali.colombiancaliycali.dao.FacturasProcesadasCuentasDao;
import com.colombia.cali.colombiancaliycali.dao.SecuenciasMysqlDao;
import com.colombia.cali.colombiancaliycali.dao.SubPrincipalDao;
import com.colombia.cali.colombiancaliycali.dao.TrasladoDao;
import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaTrasladoDTO;
import com.colombian.cali.colombiancaliycali.dto.FacturaAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaReporteSedeDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaTotalReporteDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaVentaDTO;
import com.colombian.cali.colombiancaliycali.dto.TrasladosDto;
import com.colombian.cali.colombiancaliycali.dto.VentasTotalesDTO;
import com.colombian.cali.colombiancaliycali.entidades.CambioSede;
import com.colombian.cali.colombiancaliycali.entidades.Factura;
import com.colombian.cali.colombiancaliycali.entidades.FacturasCompras;
import com.colombian.cali.colombiancaliycali.entidades.FacturasProcesadasCuentas;
import com.colombian.cali.colombiancaliycali.entidades.Inventario;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.entidades.Subprincipal;
import com.colombian.cali.colombiancaliycali.entidades.Traslado;
import com.colombian.cali.colombiancaliycali.mapper.CambioSedeMapper;
import com.colombian.cali.colombiancaliycali.mapper.FacturaMapper;
import com.colombian.cali.colombiancaliycali.mapper.TrasladoMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class FacturasServiceImpl implements FacturasService {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    @Autowired
    SedesService sedesService;
    @Autowired
    SecurityService securityService;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private TrasladoDao trasladoDao;
    @Autowired
    private CambioSedeDao cambioSedeDao;
    @Autowired
    private FacturasComprasDao facturasComprasDao;
    @Autowired
    private SecuenciasMysqlDao secuenciasMysqlDao;
    @Autowired
    private ComprasService comprasService;
    @Autowired
    private SubPrincipalDao subPrincipalDao;
    @Autowired
    private FacturasProcesadasCuentasDao facturasProcesadasCuentasDao;

    private static final String cuenta_facturas_compras_principal = "620501";
    private static final String cuenta_facturas_compras_sede = "620502";
    private static final String tipo_sede = "S";
    private static final String tipo_principal = "P";
    private static final Long id_sede_principal = 1L;
    private static final String codigo_proveedor_luz="1000";

    private static final String ESTADO_FACTURA_DEFAULT = "A";

    @Override
    @Transactional(readOnly = true)
    public Inventario traerProducto(String nameDatasource, Long idProducto) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        Inventario inventario = null;
        try {
            inventario = (Inventario) this.jdbctemplate.queryForObject(caliycaliDao.selectJdbTemplate("*", "inventario", "codigo_producto_inventario= ?"), new Object[]{idProducto}, new BeanPropertyRowMapper(Inventario.class));
        } catch (DataAccessException e) {
            System.out.println("La consulta retornó 0 elementos");
        }

        return inventario;

    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaVentaDTO> traerProductosFactura(String datasource, Long idFactura) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(datasource));
        List<FacturaVentaDTO> detalleFactura = null;

        try {
            detalleFactura = this.jdbctemplate.query("SELECT df.codigo_producto_inventario as codigo, "
                    + " inv.descripcion_producto as producto, "
                    + " df.numero_unidades as unidades, "
                    + " inv.promedio as valorUnitario, "
                    + " df.valor_producto as totalProducto,f.idsede as idsede,f.estado_factura as estado FROM detalle_factura df "
                    + " inner join inventario inv on inv.codigo_producto_inventario = df.codigo_producto_inventario "
                    + " inner join factura f on f.numero_factura = df.numero_factura "
                    + " where df.numero_factura = ? order by secuencial_factura", new Object[]{idFactura}, new BeanPropertyRowMapper(FacturaVentaDTO.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR traerProductosFactura: La consulta retornó 0 elementos " + e.getMessage());
        }

        return detalleFactura;
    }

    @Override
    @Transactional
    public List<VentasTotalesDTO> ventasTotales(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<VentasTotalesDTO> reporte = null;
        String sql = "select subCompras3.*,(subCompras3.valor/subCompras3.unidades) as promedio from (select subFacturas2.codigo,subFacturas2.producto, sum(subFacturas2.unidades) as unidades, subFacturas2.promedio as promedio, sum(subFacturas2.valor) as valor "
                + " from(select subFacturas.codigo as codigo ,subFacturas.producto as producto "
                + " , case when subFacturas.unidades is null then 0 else subFacturas.unidades end as unidades, "
                + " case when subFacturas.estado_factura is null then '" + estadoCompra + "' else subFacturas.estado_factura end as estado_factura, "
                + " case when subFacturas.fecha_factura is null or subFacturas.fecha_factura = '' then '" + fechaInicial + "' else subFacturas.fecha_factura end as fecha_factura  , "
                + " case when subFacturas.promedio is null then 0 else subFacturas.promedio end as promedio, "
                + " case when subFacturas.valor is null then 0 else subFacturas.valor end as valor "
                + " from (select i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "
                + " df.numero_unidades as unidades,i.promedio as promedio,df.valor_producto as valor,f.estado_factura ,df.fecha_factura  "
                + " from inventario i inner join detalle_factura df on i.codigo_producto_inventario = df.codigo_producto_inventario "
                + " inner join factura f on df.numero_factura = f.numero_factura)subFacturas "
                + " )subFacturas2 where subFacturas2.estado_factura = '" + estadoCompra + "' and subFacturas2.fecha_factura between '" + fechaInicial + "' and '" + fechaFinal + "'  "
                + " group by subFacturas2.codigo,subFacturas2.producto) subCompras3 order by 1";

        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper(VentasTotalesDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        return reporte;
    }

    @Override
    @Transactional
    public List<VentasTotalesDTO> ventasTotalesSede(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long idSede) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<VentasTotalesDTO> reporte = null;
        String sql = "select subCompras3.*,(subCompras3.valor/subCompras3.unidades) as promedio from (select subFacturas2.codigo,subFacturas2.producto, sum(subFacturas2.unidades) as unidades, subFacturas2.promedio as promedio, sum(subFacturas2.valor) as valor "
                + " from(select subFacturas.codigo as codigo ,subFacturas.producto as producto "
                + " , case when subFacturas.unidades is null then 0 else subFacturas.unidades end as unidades, "
                + " case when subFacturas.estado_factura is null then '" + estadoCompra + "' else subFacturas.estado_factura end as estado_factura, "
                + " case when subFacturas.fecha_factura is null or subFacturas.fecha_factura = '' then '" + fechaInicial + "' else subFacturas.fecha_factura end as fecha_factura  , "
                + " case when subFacturas.promedio is null then 0 else subFacturas.promedio end as promedio, "
                + " case when subFacturas.valor is null then 0 else subFacturas.valor end as valor "
                + " from (select i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "
                + " df.numero_unidades as unidades,i.promedio as promedio,df.valor_producto as valor,f.estado_factura ,df.fecha_factura  "
                + " from inventario i inner join detalle_factura df on i.codigo_producto_inventario = df.codigo_producto_inventario "
                + " inner join factura f on df.numero_factura = f.numero_factura and f.idsede = " + idSede.longValue() + ")subFacturas "
                + " )subFacturas2 where subFacturas2.estado_factura = '" + estadoCompra + "' and subFacturas2.fecha_factura between '" + fechaInicial + "' and '" + fechaFinal + "'  "
                + " group by subFacturas2.codigo,subFacturas2.producto) subCompras3  order by 1";

        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper(VentasTotalesDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        return reporte;
    }

    @Override
    @Transactional
    public List<FacturaVentaDTO> detalleFacturaVenta(String nameDataSource, Long numeroFactura) {

        List<FacturaVentaDTO> detalleFactura = null;
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));

        String sql = "SELECT i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "
                + " df.numero_unidades as unidades,i.promedio as valorUnitario,df.valor_producto as totalProducto FROM factura f "
                + " inner join detalle_factura df on df.numero_factura = f.numero_factura "
                + " inner join inventario i on i.codigo_producto_inventario = df.codigo_producto_inventario "
                + " where f.numero_factura = ?";

        try {
            detalleFactura = this.jdbctemplate.query(sql, new Object[]{numeroFactura}, new BeanPropertyRowMapper(FacturaVentaDTO.class));
        } catch (DataAccessException e) {
            System.out.println("La consulta retornó 0 elementos");
        }
        return detalleFactura;
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFactura(String nameDataSource, Long numeroFactura) {
        Factura factura = null;
        factura = facturaDao.findFactura(nameDataSource, numeroFactura);
        return factura;
    }

    @Override
    @Transactional
    public String borrarFactura(String nameDataSource, Long numeroFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));

        facturaDao.borrarFactura(nameDataSource, numeroFactura);
        facturaDao.borrarDetalleFactura(nameDataSource, numeroFactura);

        return "true";
    }

    @Override
    @Transactional
    public void guardarFactura(String nameDatasource, String nombreSede, DetalleFacturaDTO detalleFacturaDTO) {
        
        //Principal
        facturaDao.insertarFacturaNueva(nameDatasource, detalleFacturaDTO, detalleFacturaDTO.getSede(), ESTADO_FACTURA_DEFAULT);
        Long secuenciaPrincipal = facturaDao.secuenciaDetalle(nameDatasource);
        facturaDao.insertarDetalle(nameDatasource, detalleFacturaDTO, detalleFacturaDTO.getSede(), ESTADO_FACTURA_DEFAULT, secuenciaPrincipal);
        detalleFacturaDTO.setNumeroFactura("" + secuenciaPrincipal);
        //Principal Sede 
        Subprincipal subprincipal = subPrincipalDao.findSubPrincipalByIdsede(nameDatasource, detalleFacturaDTO.getSede().intValue());
        DetalleFacturaDTO detalleFacturaDTOPrincipalSede = new DetalleFacturaDTO();
        detalleFacturaDTOPrincipalSede.setFactura(detalleFacturaDTO.getFactura());
        detalleFacturaDTOPrincipalSede.setFechaFactura(detalleFacturaDTO.getFechaFactura());
        detalleFacturaDTOPrincipalSede.setNumeroFactura("");
        Sedes sedesPrincipalSede = sedesService.buscarSedeXNombre(subprincipal.getNombre(), nombreSede);
        detalleFacturaDTOPrincipalSede.setSede(sedesPrincipalSede.getIdsedes());
        detalleFacturaDTOPrincipalSede.setTotalFactura("0");

        facturaDao.insertarFacturaNueva(subprincipal.getNombre(), detalleFacturaDTOPrincipalSede, detalleFacturaDTOPrincipalSede.getSede(), ESTADO_FACTURA_DEFAULT);
        Long secuenciaPrincipalSede = facturaDao.secuenciaDetalle(subprincipal.getNombre());
        facturaDao.insertarDetalle(subprincipal.getNombre(), detalleFacturaDTOPrincipalSede, detalleFacturaDTOPrincipalSede.getSede(), ESTADO_FACTURA_DEFAULT, secuenciaPrincipalSede);
        //Sede  
        detalleFacturaDTOPrincipalSede.setTotalFactura(detalleFacturaDTO.getTotalFactura());
        facturaDao.insertarFacturaSede(nombreSede, detalleFacturaDTOPrincipalSede, ESTADO_FACTURA_DEFAULT, secuenciaPrincipalSede);
        facturaDao.insertarDetalleSede(nombreSede, detalleFacturaDTOPrincipalSede, ESTADO_FACTURA_DEFAULT, secuenciaPrincipalSede);
        detalleFacturaDTOPrincipalSede.setNumeroFactura("" + secuenciaPrincipalSede);
        
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        detalleCompraDTO.setCodigoProveedor(codigo_proveedor_luz);
        detalleCompraDTO.setEstadoCompraProveedor("N");
        detalleCompraDTO.setFactura(detalleFacturaDTO.getFactura());
        detalleCompraDTO.setFechaVencimiento(detalleFacturaDTO.getFechaFactura());
        Long secuenciaCompra = secuenciasMysqlDao.secuenceTable(subprincipal.getNombre(), "compras");
        detalleCompraDTO.setNumeroFactura(""+(9999150+secuenciaCompra));
        detalleCompraDTO.setTotalFactura(detalleFacturaDTO.getTotalFactura());
        detalleCompraDTO.setSaldo(Double.valueOf(detalleFacturaDTO.getTotalFactura()));
        
        comprasService.guardarCompra(subprincipal.getNombre(), detalleCompraDTO);
        /**
         * FACTURAS COMPRAS
         * **********************************************************************************************************************
         */
        //PRINCIPAL
        Long secuFacturasComprasPrincipal = secuenciasMysqlDao.secuenceTable(nameDatasource, "facturas_compras");
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasComprasPrincipal = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasComprasPrincipal.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.guardarFacturaComprasDao(nameDatasource, facturasComprasPrincipal);
        
        facturasComprasPrincipal.setConsecutivo(secuFacturasComprasPrincipal);
        FacturasProcesadasCuentas facturasProcesadasSedePrincipal = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasComprasPrincipal);
        facturasProcesadasSedePrincipal.setTipo(tipo_sede);
        facturasProcesadasCuentasDao.guardarFacturaProcesada(nameDatasource, facturasProcesadasSedePrincipal);
        //2
        //secuFacturasComprasPrincipal = secuenciasMysqlDao.secuenceTable(nameDatasource, "facturas_compras"); 
        facturasComprasPrincipal.setTotal(facturasComprasPrincipal.getTotal()*-1); 
        facturasComprasPrincipal.setIdsede(id_sede_principal); 
        facturasComprasPrincipal.setIdcuenta(cuenta_facturas_compras_principal); 
        facturasComprasDao.guardarFacturaComprasDao(nameDatasource, facturasComprasPrincipal); 
        
        
        /*facturasComprasPrincipal.setConsecutivo(secuFacturasComprasPrincipal);
        facturasProcesadasSedePrincipal = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasComprasPrincipal);
        facturasProcesadasSedePrincipal.setTipo(tipo_sede);
        facturasProcesadasCuentasDao.guardarFacturaProcesada(nameDatasource, facturasProcesadasSedePrincipal);*/
        //SEDE PRINCIPAL
        detalleFacturaDTOPrincipalSede.setTotalFactura("0");
        Long secuenciFacturaComprasPrincipalSede = secuenciasMysqlDao.secuenceTable(subprincipal.getNombre(), "facturas_compras");
        FacturasCompras facturasComprasPrincipalSede = facturaMapper.facturaToFacturaCompras(detalleFacturaDTOPrincipalSede);
        facturasComprasPrincipalSede.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.guardarFacturaComprasDao(subprincipal.getNombre(), facturasComprasPrincipalSede);

        facturasComprasPrincipalSede.setConsecutivo(secuenciFacturaComprasPrincipalSede);
        FacturasProcesadasCuentas facturasProcesadasSedePrincipalSede = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasComprasPrincipalSede);
        facturasProcesadasSedePrincipalSede.setTipo(tipo_sede);
        facturasProcesadasCuentasDao.guardarFacturaProcesada(subprincipal.getNombre(), facturasProcesadasSedePrincipalSede);
        
        //Inserto en facturas_compras el registro de la principal 
        //secuenciFacturaComprasPrincipalSede = secuenciasMysqlDao.secuenceTable(subprincipal.getNombre(), "facturas_compras"); 
        facturasComprasPrincipalSede.setTotal(facturasComprasPrincipalSede.getTotal()*-1); 
        facturasComprasPrincipalSede.setIdsede(id_sede_principal); 
        facturasComprasPrincipalSede.setIdcuenta(cuenta_facturas_compras_principal); 
        facturasComprasDao.guardarFacturaComprasDao(subprincipal.getNombre(), facturasComprasPrincipalSede); 
         
        /*facturasComprasPrincipalSede.setConsecutivo(secuenciFacturaComprasPrincipalSede); 
        FacturasProcesadasCuentas facturasProcesadasPrincipal = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasComprasPrincipalSede); 
        facturasProcesadasSedePrincipalSede.setTipo(tipo_principal); 
        facturasProcesadasCuentasDao.guardarFacturaProcesada(subprincipal.getNombre(), facturasProcesadasPrincipal); */
        
    }

    @Override
    @Transactional
    public void actualizarFactura(String nameDatasource, String nombreSede, String estadoFactura, DetalleFacturaDTO detalleFacturaDTO) {
        Long numeroFactura = Long.parseLong(detalleFacturaDTO.getNumeroFactura());

        //Traigo los consecutivos a actualizar
        List<FacturasProcesadasCuentas> facturasProcesadasCuentas = facturasProcesadasCuentasDao.buscarFacturaProcesada(nameDatasource, numeroFactura);

        //Inserto en factura
        System.out.println("INSERTAR FACTURA::");
        facturaDao.insertarFactura(nameDatasource, detalleFacturaDTO, detalleFacturaDTO.getSede(), estadoFactura);
        System.out.println("FECHA2::" + detalleFacturaDTO.getFechaFactura());
        facturaDao.insertarDetalle(nameDatasource, detalleFacturaDTO, detalleFacturaDTO.getSede(), estadoFactura, numeroFactura);

        facturaDao.insertarFacturaSede(nombreSede, detalleFacturaDTO, estadoFactura, numeroFactura);
        facturaDao.insertarDetalleSede(nombreSede, detalleFacturaDTO, estadoFactura, numeroFactura);
        //String insertFactura = guardarFacturaSede(nombreSede, detalleFacturaDTO, estadoFactura);

        //Actualizo en facturas_compras  Sede
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasComprasSede = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasComprasSede.setConsecutivo(facturasProcesadasCuentas.get(0).getIdfacturacompra());
        facturasComprasSede.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.actualizarFacturaComprasDao(nameDatasource, facturasComprasSede);

        //Actualizo en facturas_compras  Principal
        FacturasCompras facturasComprasPrincipal = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasComprasPrincipal.setConsecutivo(facturasProcesadasCuentas.get(1).getIdfacturacompra());
        facturasComprasPrincipal.setIdsede(id_sede_principal);
        facturasComprasPrincipal.setTotal(facturasComprasPrincipal.getTotal() * -1);
        facturasComprasPrincipal.setIdcuenta(cuenta_facturas_compras_principal);
        facturasComprasDao.actualizarFacturaComprasDao(nameDatasource, facturasComprasPrincipal);
    }

    @Override
    @Transactional
    public void cambiarSedeFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, Sedes sedeOrigen, Sedes sedeDestino) {
        //Actualizo en principal
        facturaDao.actualizarSedeFactura(nameDataSource, detalleFacturaDTO, estadoFactura);
        facturaDao.actualizarSedeDetalleFactura(nameDataSource, detalleFacturaDTO, estadoFactura);
        //actualizarFactura(nameDataSource, detalleFacturaDTO, estadoFactura);
        //Inserto en destino
        Long numeroFactura = Formatos.convertToLong(detalleFacturaDTO.getNumeroFactura());
        facturaDao.insertarFacturaSede(sedeDestino.getSede(), detalleFacturaDTO, estadoFactura, numeroFactura);
        facturaDao.insertarDetalleSede(sedeDestino.getSede(), detalleFacturaDTO, estadoFactura, numeroFactura);

        //String insertFactura = guardarFacturaSede(sedeDestino.getSede(), detalleFacturaDTO, estadoFactura);
        //Inserto en Principal el traslado
        CambioSedeMapper cambioSedeMapper = new CambioSedeMapper();
        CambioSede traslado = cambioSedeMapper.detalleDtoTOtraslado(detalleFacturaDTO);
        traslado.setSedeOrigen(sedeOrigen.getIdsedes());
        cambioSedeDao.guardarCambioSede(nameDataSource, traslado);
        //Borro en Origen
        facturaDao.borrarFactura(sedeOrigen.getSede(), numeroFactura);
        facturaDao.borrarDetalleFactura(sedeOrigen.getSede(), numeroFactura);

        //Actualizo la sede en facturas_compras
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasCompras = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        FacturasProcesadasCuentas facturasProcesadasCuentas = facturasProcesadasCuentasDao.buscarFacturaProcesadaSedes(nameDataSource, numeroFactura);
        facturasCompras.setConsecutivo(facturasProcesadasCuentas.getIdfacturacompra());
        facturasCompras.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.actualizarFacturaComprasDao(nameDataSource, facturasCompras);
    }

    @Override
    @Transactional
    public List<DetalleFacturaDTO> trasladarFactura(String nameDataSource, DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<DetalleFacturaDTO> facturasTrasladadas = new ArrayList<DetalleFacturaDTO>();
        /**
         * INSERTO EN BD PRINCIPAL*
         */
        //Inserto en origen principal y sede
        Sedes sedeOrigen = sedesService.buscarSede(nameDataSource, detalleFacturaTrasladoDTO.getSedeOrigen());
        TrasladoMapper trasladoMapper = new TrasladoMapper();
        DetalleFacturaDTO detalleFacturaDTOOrigen = trasladoMapper.DetalleFacturaTrasladoDtoToDetalleFacturaDTO(detalleFacturaTrasladoDTO, "origen");
        guardarFactura(nameDataSource, sedeOrigen.getSede(), detalleFacturaDTOOrigen);
        facturasTrasladadas.add(detalleFacturaDTOOrigen);

        //Inserto en destino principal y sede
        Sedes sedeDestino = sedesService.buscarSede(nameDataSource, detalleFacturaTrasladoDTO.getSedeDestino());
        DetalleFacturaDTO detalleFacturaDTODestino = trasladoMapper.DetalleFacturaTrasladoDtoToDetalleFacturaDTO(detalleFacturaTrasladoDTO, "destino");
        guardarFactura(nameDataSource, sedeDestino.getSede(), detalleFacturaDTODestino);
        facturasTrasladadas.add(detalleFacturaDTODestino);
        //Traslado
        Traslado traslado = trasladoMapper.detalleFacturaTrasladoDtoToTraslado(detalleFacturaTrasladoDTO);
        traslado.setUsuario(securityService.getCurrentUser().getCedula());
        traslado.setNumeroFacturaDestino(Long.parseLong(detalleFacturaDTODestino.getNumeroFactura()));
        traslado.setNumeroFacturaOrigen(Long.parseLong(detalleFacturaDTOOrigen.getNumeroFactura()));
        trasladoDao.insertarTraslado(nameDataSource, traslado);

        return facturasTrasladadas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaReporteSedeDto> reporteFacturaCompraProveedor(String nameDataSource, Long idsede, String fechaInicio, String fechaFin) {
        return facturaDao.reporteTotalFacturaXSede(nameDataSource, fechaInicio, fechaFin, idsede);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaTotalReporteDto> reporteFacturaCompra(String nameDataSource, String fechaInicio, String fechaFin) {
        return facturaDao.reporteTotalFactura(nameDataSource, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrasladosDto> reporteTraslados(String nameDataSource, Date fechaInicio, Date fechaFin) {
        return trasladoDao.reporteTraslado(nameDataSource, fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(String nameDataSource, String numeroFactura, Long idproveedor) {
        return facturaDao.buscarFacturaAutocompletar(nameDataSource, numeroFactura, idproveedor);
    }
}
