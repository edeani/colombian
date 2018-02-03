/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.InventarioDao;
import com.administracion.dao.ReportesDao;
import com.administracion.dto.InventarioDTO;
import com.administracion.dto.InventarioFinalDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteInventarioDTO;
import com.administracion.service.autorizacion.ConnectsAuth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    private SedesService sedesService;
    
    @Autowired
    private InventarioDao inventarioDao;
    
    @Autowired
    private ReportesDao reportesDao;
    
    @Autowired
    private ConnectsAuth connectsAuth;
    
    private JdbcTemplate jdbctemplate;
    
    @Override
    @Transactional(readOnly=true)
    public List<ReporteInventarioDTO> reporteInventario(String nameDataSource,String fecha) {
        
        String[] fechas = fecha.split(",");
        List<ReporteInventarioDTO> reporteInventarioDTOs=null;
        try{
        reporteInventarioDTOs= reportesDao.findInventarioXFechaFinal(connectsAuth.getDataSourceSede(nameDataSource), fechas[0]);
        
        }catch(DataAccessException e){
            System.out.println("Reporte Inventario: Se encontraron 0 registros");
        }
        return reporteInventarioDTOs;
    }

    @Override
    @Transactional(readOnly=true)
    public List<InventarioDTO> reporteInventario(String nameDataSource) {
    
        List<InventarioDTO> inventarioDTO=null;
        try{
        inventarioDTO= inventarioDao.listInventarioDto(connectsAuth.getDataSourceSede(nameDataSource));
        
        }catch(DataAccessException e){
            System.out.println("Inventario: Se encontraron 0 registros");
        }
        return inventarioDTO;
    }

    @Override
    @Transactional
    public void eliminarProducto(String dataSource, Long idProducto) {
        try {
            inventarioDao.eliminarProducto(connectsAuth.getDataSourceSede(dataSource), idProducto);
        } catch (DataAccessException e) {
            System.out.println("No se eliminó el producto");
        }
        
    }
    
    @Override
    @Transactional
    public void insertarProducto(String nameDataSource, InventarioDTO inventarioDTO) {
        inventarioDao.insertarProducto(connectsAuth.getDataSourceSede(nameDataSource), inventarioDTO);
    }

    @Override
    @Transactional
    public void actualizarProducto(String nameDataSource, InventarioDTO inventarioDTO) {
        inventarioDao.actualizarProducto(connectsAuth.getDataSourceSede(nameDataSource), inventarioDTO);
    }

    @Override
    @Transactional(readOnly=true)
    public InventarioDTO traerProducto(String nameDatasource, Long idProducto) {
        return inventarioDao.traerProductoDto(connectsAuth.getDataSourceSede(nameDatasource), idProducto);
    }

    @Override
    @Transactional
    public boolean actualizarPromedioInventario(String nameDatasource, Long idProducto, Double promedio) {
       inventarioDao.actualizarPromedio(connectsAuth.getDataSourceSede(nameDatasource), idProducto, promedio);
       return true;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Double calcularPromedioInventario(String nameDatasource, Long idProducto,String fechaini,String fechafin){
        return inventarioDao.promedioInventario(connectsAuth.getDataSourceSede(nameDatasource), idProducto, fechaini, fechafin);
    }

    @Override
    @Transactional(readOnly=true)
    public List<InventarioFinalDTO> reporteInventarioFinal(String nameDatasource, String fechaInicial, String fechaFinal) {
        return inventarioDao.inventarioFinal(connectsAuth.getDataSourceSede(nameDatasource), fechaInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> listaProductoOptions(String nameDatasource) {
        return inventarioDao.listaProductoOptions(connectsAuth.getDataSourceSede(nameDatasource));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> listaProductosLabel(String nameDatasource) {
        return inventarioDao.listaProductosLabel(connectsAuth.getDataSourceSede(nameDatasource));
    }
}