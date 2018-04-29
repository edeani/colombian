/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.adiministracion.mapper.CambioSedeMapper;
import com.adiministracion.mapper.FacturaMapper;
import com.adiministracion.mapper.TrasladoMapper;
import com.administracion.dao.CambioSedeDao;
import com.administracion.dao.FacturaDao;
import com.administracion.dao.FacturasComprasDao;
import com.administracion.dao.FacturasProcesadasCuentasDao;
import com.administracion.dao.InventarioDao;
import com.administracion.dao.SecuenciasMysqlDao;
import com.administracion.dao.TrasladoDao;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.DetalleFacturaTrasladoDTO;
import com.administracion.dto.FacturaAutocompletarDto;
import com.administracion.dto.FacturaReporteSedeDto;
import com.administracion.dto.FacturaTotalReporteDto;
import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.dto.TrasladosDto;
import com.administracion.dto.VentasTotalesDTO;
import com.administracion.entidad.CambioSede;
import com.administracion.entidad.Factura;
import com.administracion.entidad.FacturasCompras;
import com.administracion.entidad.FacturasProcesadasCuentas;
import com.administracion.entidad.Inventario;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.Traslado;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.Formatos;
import java.util.ArrayList;
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
 * @author edeani
 */
@Service
public class FacturasServiceImpl implements FacturasService {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private InventarioDao inventarioDao;
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
    private FacturasProcesadasCuentasDao facturasProcesadasCuentasDao;
    
    private DataSource principalDataSource;
    
    @Autowired
    private void init(DataSource dataSourceSede){
        this.principalDataSource = dataSourceSede;
    }
    
    private  final String cuenta_facturas_compras_principal = "620501";
    private  final String cuenta_facturas_compras_sede = "620502";
    private  final String tipo_sede = "S";
    private  final String tipo_principal = "P";
    private  final Long id_sede_principal=1L;

    private static final String ESTADO_FACTURA_DEFAULT = "A";

    @Override
    @Transactional(readOnly = true)
    public Inventario traerProducto(String nameDatasource, Long idProducto) {
        return inventarioDao.traerProducto(connectsAuth.getDataSourceSede(nameDatasource), idProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaVentaDTO> traerProductosFactura(String datasource, Long idFactura) {
        return inventarioDao.traerProductosFactura(connectsAuth.getDataSourceSede(datasource), idFactura);
    }

    @Override
    @Transactional
    public List<VentasTotalesDTO> ventasTotales(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra) {
        return facturaDao.ventasTotales(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra);
    }

    @Override
    @Transactional
    public List<VentasTotalesDTO> ventasTotalesSede(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long idSede) {
        return facturaDao.ventasTotalesSede(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal, estadoCompra, idSede);
    }

    @Override
    @Transactional
    public List<FacturaVentaDTO> detalleFacturaVenta(String nameDataSource, Long numeroFactura) {
        return facturaDao.detalleFacturaVenta(connectsAuth.getDataSourceSede(nameDataSource), numeroFactura);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaSede(String nameDataSource, Long numeroFactura) {
        return facturaDao.findFactura(connectsAuth.getDataSourceSede(nameDataSource), numeroFactura);
    }
    
    @Override
    @Transactional
    public String borrarFacturaSede(String nameDataSource, Long numeroFactura) {
        DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
        facturaDao.borrarFactura(ds, numeroFactura);
        facturaDao.borrarDetalleFactura(ds, numeroFactura);
        return "true";
    }
    
    @Override
    @Transactional
    public String borrarFacturaSubSede(String nameDataSource, Long numeroFactura) {
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        facturaDao.borrarFactura(ds, numeroFactura);
        facturaDao.borrarDetalleFactura(ds, numeroFactura);
        return "true";
    }
    
    @Override
    @Transactional
    public void guardarFactura(String nameDatasource, String nombreSede, DetalleFacturaDTO detalleFacturaDTO) {
        DataSource ds = connectsAuth.getDataSourceSede(nameDatasource);
        DataSource ds_subsede =connectsAuth.getDataSourceSubSede(nombreSede);
        //Principal
        facturaDao.insertarFacturaNueva(ds, detalleFacturaDTO, detalleFacturaDTO.getSede(), ESTADO_FACTURA_DEFAULT);
        Long secuenciaActual = facturaDao.secuenciaDetalle(ds);
        facturaDao.insertarDetalleSede(ds, detalleFacturaDTO, detalleFacturaDTO.getSede(), ESTADO_FACTURA_DEFAULT, secuenciaActual);
        //Sede
        facturaDao.insertarFacturaSubSede(ds_subsede, detalleFacturaDTO, ESTADO_FACTURA_DEFAULT, secuenciaActual);
        facturaDao.insertarDetalleSubSede(ds_subsede, detalleFacturaDTO, ESTADO_FACTURA_DEFAULT, secuenciaActual);
        detalleFacturaDTO.setNumeroFactura("" + secuenciaActual);
        
        //Inserto en facturas_compras el registro de sede
        Long secuenciFacturaCompras = secuenciasMysqlDao.secuenceTable(ds, "facturas_compras");
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasCompras = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasCompras.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.guardarFacturaComprasDao(ds, facturasCompras);
        
        facturasCompras.setConsecutivo(secuenciFacturaCompras);
        FacturasProcesadasCuentas facturasProcesadasSede = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasCompras);
        facturasProcesadasSede.setTipo(tipo_sede);
        facturasProcesadasCuentasDao.guardarFacturaProcesada(ds, facturasProcesadasSede);
        
        //Inserto en facturas_compras el registro de la principal
        secuenciFacturaCompras = secuenciasMysqlDao.secuenceTable(ds, "facturas_compras");
        facturasCompras.setTotal(facturasCompras.getTotal()*-1);
        SedesDto sedesDto = connectsAuth.findSedeXName(nameDatasource);
        facturasCompras.setIdsede(sedesDto.getIdsedes().longValue());
        facturasCompras.setIdcuenta(cuenta_facturas_compras_principal);
        facturasComprasDao.guardarFacturaComprasDao(ds, facturasCompras);
        
        facturasCompras.setConsecutivo(secuenciFacturaCompras);
        FacturasProcesadasCuentas facturasProcesadasPrincipal = facturaMapper.facturasComprasToFacturasProcesadasCuentas(facturasCompras);
        facturasProcesadasPrincipal.setTipo(tipo_principal);
        facturasProcesadasCuentasDao.guardarFacturaProcesada(ds, facturasProcesadasPrincipal);
    }

    @Override
    @Transactional
    public void actualizarFactura(String nameDatasource, String nombreSede, String estadoFactura, DetalleFacturaDTO detalleFacturaDTO) {
        Long numeroFactura = Long.parseLong(detalleFacturaDTO.getNumeroFactura());
        DataSource ds = connectsAuth.getDataSourceSede(nameDatasource);
        DataSource ds_subsedes =connectsAuth.getDataSourceSubSede(nombreSede);
        //Traigo los consecutivos a actualizar
        List<FacturasProcesadasCuentas> facturasProcesadasCuentas = facturasProcesadasCuentasDao.buscarFacturaProcesada(ds, numeroFactura);
        
        //Inserto en factura Sede
        System.out.println("INSERTAR FACTURA::");
        facturaDao.insertarFacturaSede(ds, detalleFacturaDTO, detalleFacturaDTO.getSede(), estadoFactura);
        System.out.println("FECHA2::"+detalleFacturaDTO.getFechaFactura());
        facturaDao.insertarDetalleSede(ds, detalleFacturaDTO, detalleFacturaDTO.getSede(), estadoFactura, numeroFactura);
        //Inserto en factura SubSede
        facturaDao.insertarFacturaSubSede(ds_subsedes, detalleFacturaDTO, estadoFactura, numeroFactura);
        facturaDao.insertarDetalleSubSede(ds_subsedes, detalleFacturaDTO, estadoFactura, numeroFactura);
        //String insertFactura = guardarFacturaSede(nombreSede, detalleFacturaDTO, estadoFactura);
        
        //Actualizo en facturas_compras  Sede
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasComprasSede = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasComprasSede.setConsecutivo(facturasProcesadasCuentas.get(0).getIdfacturacompra());
        facturasComprasSede.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.actualizarFacturaComprasDao(ds, facturasComprasSede);
        
        //Actualizo en facturas_compras  Principal
        FacturasCompras facturasComprasPrincipal = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        facturasComprasPrincipal.setConsecutivo(facturasProcesadasCuentas.get(1).getIdfacturacompra());
        facturasComprasPrincipal.setIdsede(id_sede_principal);
        facturasComprasPrincipal.setTotal(facturasComprasPrincipal.getTotal()*-1);
        facturasComprasPrincipal.setIdcuenta(cuenta_facturas_compras_principal);
        facturasComprasDao.actualizarFacturaComprasDao(ds, facturasComprasPrincipal);
    }


    @Override
    @Transactional
    public void cambiarSedeFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, SubSedesDto sedeOrigen, SubSedesDto sedeDestino) {
        DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
        //Actualizo en principal
        facturaDao.actualizarSedeFactura(ds, detalleFacturaDTO, estadoFactura);
        facturaDao.actualizarSedeDetalleFactura(ds, detalleFacturaDTO, estadoFactura);
        //actualizarFactura(nameDataSource, detalleFacturaDTO, estadoFactura);
        //Inserto en destino
        DataSource dsDestino = connectsAuth.getDataSourceSubSede(sedeDestino.getSede());
        Long numeroFactura = Formatos.convertToLong(detalleFacturaDTO.getNumeroFactura());
        facturaDao.insertarFacturaSubSede(dsDestino, detalleFacturaDTO, estadoFactura,numeroFactura);
        facturaDao.insertarDetalleSubSede(dsDestino, detalleFacturaDTO, estadoFactura, numeroFactura);
        
        //String insertFactura = guardarFacturaSede(sedeDestino.getSede(), detalleFacturaDTO, estadoFactura);
        //Inserto en Principal el traslado
        CambioSedeMapper cambioSedeMapper = new CambioSedeMapper();
        CambioSede traslado = cambioSedeMapper.detalleDtoTOtraslado(detalleFacturaDTO);
        traslado.setSedeOrigen(sedeOrigen.getIdsedepoint().longValue());
        cambioSedeDao.guardarCambioSede(ds, traslado);  
        //Borro en Origen
        DataSource dsOrigen = connectsAuth.getDataSourceSubSede(sedeOrigen.getSede());
        facturaDao.borrarFactura(dsOrigen, numeroFactura);
        facturaDao.borrarDetalleFactura(dsOrigen, numeroFactura);
        
        //Actualizo la sede en facturas_compras
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturasCompras facturasCompras = facturaMapper.facturaToFacturaCompras(detalleFacturaDTO);
        FacturasProcesadasCuentas facturasProcesadasCuentas = facturasProcesadasCuentasDao.buscarFacturaProcesadaSedes(ds, numeroFactura);
        facturasCompras.setConsecutivo(facturasProcesadasCuentas.getIdfacturacompra());
        facturasCompras.setIdcuenta(cuenta_facturas_compras_sede);
        facturasComprasDao.actualizarFacturaComprasDao(ds, facturasCompras);
    }

    @Override
    @Transactional
    public List<DetalleFacturaDTO> trasladarFactura(String nameDataSource, DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO) {
        DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
        List<DetalleFacturaDTO> facturasTrasladadas = new ArrayList<>();
        /**
         * INSERTO EN BD PRINCIPAL*
         */
        //Inserto en origen principal y sede
        SedesDto sedePrincipal = connectsAuth.findSedeXName(nameDataSource);
        SubSedesDto subSedePrincipalOrigen = connectsAuth.finSubsedeXIdCredencials(sedePrincipal.getIdsedes(), detalleFacturaTrasladoDTO.getSedeOrigen().intValue());
        TrasladoMapper trasladoMapper = new TrasladoMapper();
        DetalleFacturaDTO detalleFacturaDTOOrigen = trasladoMapper.DetalleFacturaTrasladoDtoToDetalleFacturaDTO(detalleFacturaTrasladoDTO, "origen");
        guardarFactura(nameDataSource, subSedePrincipalOrigen.getSede(), detalleFacturaDTOOrigen);
        facturasTrasladadas.add(detalleFacturaDTOOrigen);

        //Inserto en destino principal y sede
        SubSedesDto subSedePrincipalDestino = connectsAuth.finSubsedeXIdCredencials(sedePrincipal.getIdsedes(), detalleFacturaTrasladoDTO.getSedeDestino().intValue());
        DetalleFacturaDTO detalleFacturaDTODestino = trasladoMapper.DetalleFacturaTrasladoDtoToDetalleFacturaDTO(detalleFacturaTrasladoDTO, "destino");
        guardarFactura(nameDataSource, subSedePrincipalDestino.getSede(), detalleFacturaDTODestino);
        facturasTrasladadas.add(detalleFacturaDTODestino);
        //Traslado
        Traslado traslado = trasladoMapper.detalleFacturaTrasladoDtoToTraslado(detalleFacturaTrasladoDTO);
        traslado.setUsuario(securityService.getCurrentUser().getCedula());
        traslado.setNumeroFacturaDestino(Long.parseLong(detalleFacturaDTODestino.getNumeroFactura()));
        traslado.setNumeroFacturaOrigen(Long.parseLong(detalleFacturaDTOOrigen.getNumeroFactura()));
        trasladoDao.insertarTraslado(ds, traslado);

        return facturasTrasladadas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaReporteSedeDto> reporteFacturaCompraProveedor(String nameDataSource, Long idsede, String fechaInicio, String fechaFin) {
        return facturaDao.reporteTotalFacturaXSede(connectsAuth.getDataSourceSede(nameDataSource), fechaInicio, fechaFin, idsede);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaTotalReporteDto> reporteFacturaCompra(String nameDataSource, String fechaInicio, String fechaFin) {
        return facturaDao.reporteTotalFactura(connectsAuth.getDataSourceSede(nameDataSource), fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrasladosDto> reporteTraslados(String nameDataSource, Date fechaInicio, Date fechaFin) {
        return trasladoDao.reporteTraslado(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicio, fechaFin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(String nameDataSource, String numeroFactura, Long idproveedor) {
        return facturaDao.buscarFacturaAutocompletar(connectsAuth.getDataSourceSubSede(nameDataSource), numeroFactura, idproveedor);
    }
}
