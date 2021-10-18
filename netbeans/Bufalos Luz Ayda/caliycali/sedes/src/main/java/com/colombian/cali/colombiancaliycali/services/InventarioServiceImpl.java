/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.InventarioDao;
import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.InventarioDTO;
import com.colombian.cali.colombiancaliycali.dto.InventarioFinalDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteInventarioDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    CaliycaliDao caliyCaliDao;
    @Autowired
    private ProjectsDao projectsDao; 
    @Autowired
    private SedesService sedesService;
    
    @Autowired
    private InventarioDao inventarioDao;
    
    private JdbcTemplate jdbctemplate;
    
    @Override
    @Transactional(readOnly=true)
    public List<ReporteInventarioDTO> reporteInventario(String nameDataSource,String fecha) {
        
        String[] fechas = fecha.split(",");
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ReporteInventarioDTO> reporteInventarioDTOs=null;
        try{
        reporteInventarioDTOs= this.jdbctemplate.query(caliyCaliDao.selectJdbTemplate("codigo_producto_inventario as codigoProducto,descripcion_producto as descripcionProducto,stock_real as stockFinal",
                   "inventario", "fecha_final = ? ") +" order by codigo_producto_inventario", new Object[] { fechas[0] }, new BeanPropertyRowMapper(ReporteInventarioDTO.class));
        
        }catch(Exception e){
            System.out.println("Reporte Inventario: Se encontraron 0 registros");
        }
        return reporteInventarioDTOs;
    }

    @Override
    @Transactional(readOnly=true)
    public List<InventarioDTO> reporteInventario(String nameDataSource) {
    
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<InventarioDTO> inventarioDTO=null;
        try{
        inventarioDTO= this.jdbctemplate.query(caliyCaliDao.selectJdbTemplate("*",
                   "inventario", ""), new BeanPropertyRowMapper(InventarioDTO.class));
        
        }catch(Exception e){
            System.out.println("Inventario: Se encontraron 0 registros");
        }
        return inventarioDTO;
    }

    @Override
    @Transactional
    public void eliminarProducto(String dataSource, Long idProducto) {
        
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(dataSource));
        try {
            this.jdbctemplate.execute(caliyCaliDao.deleteJdbTemplate("inventario", "codigo_producto_inventario="+idProducto.longValue()));
        } catch (Exception e) {
            System.out.println("No se eliminó el producto");
        }
        
    }
    
    @Override
    @Transactional
    public void insertarProducto(String nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliyCaliDao.insertJdbTemplate("codigo_producto_inventario,descripcion_producto,fecha_inicial,stock_minimo,stock_hoy,fecha_final,stock_real,promedio", "inventario"
                    ,inventarioDTO.getCodigoProductoInventario()+",'"+inventarioDTO.getDescripcionProducto()+"','"+
                    inventarioDTO.getFechaInicial()+"',"+inventarioDTO.getStockMinimo()+","+inventarioDTO.getStockHoy()+",'"+inventarioDTO.getFechaFinal()+"',"+inventarioDTO.getStockReal()+
                    ","+inventarioDTO.getPromedio()));
        } catch (Exception e) {
            System.out.println("No se insertó el producto");
        }
    }

    @Override
    @Transactional
    public void actualizarProducto(String nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        
        try {
            this.jdbctemplate.execute(caliyCaliDao.updateJdbTemplate("descripcion_producto='"+inventarioDTO.getDescripcionProducto()+"',fecha_inicial='"+inventarioDTO.getFechaInicial()
                      +"',stock_minimo="+inventarioDTO.getStockMinimo()+",stock_hoy="+inventarioDTO.getStockHoy()
                    +",fecha_final='"+inventarioDTO.getFechaFinal()+"',stock_real="+inventarioDTO.getStockReal()+",promedio="+inventarioDTO.getPromedio()
                    , "inventario"
                    ,"codigo_producto_inventario = "+inventarioDTO.getCodigoProductoInventario()));
        } catch (Exception e) {
            System.out.println("No se actualizó el producto");
        }
    }

    @Override
    @Transactional(readOnly=true)
    public InventarioDTO traerProducto(String nameDatasource, Long idProducto) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        InventarioDTO inventarioDTO=null;
        try{
        inventarioDTO= (InventarioDTO) this.jdbctemplate.queryForObject(caliyCaliDao.selectJdbTemplate("*",
                   "inventario", "codigo_producto_inventario = ? "), new Object[] { idProducto },new BeanPropertyRowMapper(InventarioDTO.class));
        
        }catch(Exception e){
            System.out.println("Inventario: Se encontraron 0 registros");
        }
        return inventarioDTO;
    }

    @Override
    @Transactional
    public boolean actualizarPromedioInventario(String nameDatasource, Long idProducto, Double promedio) {
       inventarioDao.actualizarPromedio(nameDatasource, idProducto, promedio);
       return true;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Double calcularPromedioInventario(String nameDatasource, Long idProducto,String fechaini,String fechafin){
        
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        Double promedio = 0D;
        try {
                promedio = this.jdbctemplate.queryForObject("SELECT detalle_compra_valor_producto/numero_unidades "
                     +"FROM( SELECT inventario.codigo_producto_inventario,  "
                     +"inventario.descripcion_producto,SUM(detalle_compra.valor_producto) detalle_compra_valor_producto,"
                     +"sum(detalle_compra.numero_unidades) numero_unidades   FROM detalle_compra,  "
                     +"compras, inventario "
                     +"WHERE ( compras.id_compra = detalle_compra.numero_compra ) and "
                     +"( inventario.codigo_producto_inventario = detalle_compra.codigo_producto_inventario ) and "
                     +"( ( compras.fecha_compra between ? and ? ) AND "
                     +"( compras.estado_compra = 'A' ) )   and inventario.codigo_producto_inventario = ? "
                     +"group by inventario.codigo_producto_inventario,inventario.descripcion_producto ) a",new Object[] { fechaini,fechafin,idProducto },Double.class);
        } catch (Exception e) {
            System.out.println("Se encontrarón 0 productos. ERROR:"+e.getMessage());
            return 0D;
        }
     
        return promedio;
    }

    @Override
    @Transactional(readOnly=true)
    public List<InventarioFinalDTO> reporteInventarioFinal(String nameDatasource, String fechaInicial, String fechaFinal) {
        
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        List<InventarioFinalDTO> reporte = null;
        String sql = "  select niveli.*,niveli.fisico - niveli.ifinal as diferencia from(  "+
                " select nivelh.codigo,nivelh.producto,nivelh.inicial,nivelh.compras,  nivelh.ventas,nivelh.averias, nivelh.inicial + nivelh.compras - nivelh.ventas - nivelh.averias as ifinal,"+
                " nivelh.stock_real as fisico  " +
                " from ( select nivelg.codigo,nivelg.producto,nivelg.inicial,nivelg.stock_real,nivelg.ventas,nivelg.compras, " +
                " case when nivelg.averias is null then 0 else nivelg.averias end averias  "+
                " from ( select nivelf.*,sum(da.numero_unidades) as averias from ( "+
                " select nivele.codigo,nivele.producto,nivele.inicial,nivele.stock_real,nivele.ventas, "+
                " case when nivele.compras is null then 0 else nivele.compras end as compras  "+
                " from ( select niveld.*,sum(dc.numero_unidades) as compras "+
                " from ( select nivelc.codigo,nivelc.producto,nivelc.inicial, "+
                " nivelc.stock_real,case when nivelc.ventas is null then 0 else nivelc.ventas end as ventas  "+
                " from (select nivelb.*,sum(df.numero_unidades) as ventas " +
                " from( select nivela.codigo,nivela.producto,  case when nivela.inicial is null then 0 else nivela.inicial end as inicial, "+
                " case when nivela.stock_real is null then 0 else nivela.stock_real end as stock_real  "+
                " from( select i.codigo_producto_inventario as codigo,i.descripcion_producto as producto, "+
                " (select inv.stock_hoy from inventario inv where inv.codigo_producto_inventario = i.codigo_producto_inventario and inv.fecha_inicial='"+fechaInicial+"') as inicial, "+
                " (select inv.stock_real from inventario inv where inv.codigo_producto_inventario = i.codigo_producto_inventario and inv.fecha_final='"+fechaFinal+"') as stock_real  "+
                " from inventario i )nivela )nivelb left join (select df.* from factura f inner join detalle_factura df on df.numero_factura = f.numero_factura "+
                " where f.fecha_factura between '"+fechaInicial+"' and '"+fechaFinal+"' AND f.estado_factura= 'A'  ) df on df.codigo_producto_inventario = nivelb.codigo "+
                " group by nivelb.codigo,nivelb.producto,nivelb.inicial,nivelb.stock_real "+
                " )nivelc )niveld left join (select dc.* from compras c inner join detalle_compra dc on c.id_compra = dc.numero_compra and c.fecha_compra  between '"+fechaInicial+"' and '"+fechaFinal+"' and c.estado_compra = 'A' ) dc "+
                " on dc.codigo_producto_inventario = niveld.codigo " +
                " group by niveld.codigo,niveld.producto,niveld.inicial,niveld.stock_real "+
                " )nivele )nivelf left join(select da.* from averias a inner join detalle_averias da on a.numero_averia=da.numero_averia and a.fecha_averia between '"+fechaInicial+"' and '"+fechaFinal+"' and a.estado_averia = 'A') da " +
                " on da.codigo_producto = nivelf.codigo  "+
                " group by nivelf.codigo,nivelf.producto,nivelf.inicial,nivelf.stock_real "+
                " )nivelg  )nivelh )niveli";
        try {
            reporte= this.jdbctemplate.query(sql, new BeanPropertyRowMapper(InventarioFinalDTO.class));
        } catch (Exception e) {
            System.out.println("Se encontrarón 0 registros. ERROR:"+e.getMessage());
        }
        
        return reporte;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> listaProductoOptions(String nameDatasource) {
        List<ItemsDTO> productos = null;
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        
        try {
            productos = this.jdbctemplate.query("select codigo_producto_inventario as id,cast(concat(codigo_producto_inventario,' ',descripcion_producto) as char) "+
                                                " as label from inventario order by descripcion_producto", new BeanPropertyRowMapper(ItemsDTO.class));
        } catch (Exception e) {
            System.out.println("Se encontraron 0 registros: "+e.getMessage());
        }
        
        return productos;
    }

    @Override
    public List<ItemsDTO> listaProductosLabel(String nameDatasource) {
        List<ItemsDTO> productos = null;
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        
        try {
            productos = this.jdbctemplate.query("select codigo_producto_inventario as id,descripcion_producto "+
                                                " as label from inventario order by codigo_producto_inventario", new BeanPropertyRowMapper(ItemsDTO.class));
        } catch (Exception e) {
            System.out.println("Se encontraron 0 registros: "+e.getMessage());
        }
        
        return productos;
    }
}