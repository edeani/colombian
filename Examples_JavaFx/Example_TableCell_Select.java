/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers;

import co.facturador.controllers.util.EditingCell;
import co.facturador.controllers.util.PrintUtil;
import co.facturador.dto.FacturaTableViewDto;
import co.facturador.dto.ItemFactura;
import co.facturador.dto.SelectItemDto;
import co.facturador.entity.Productos;
import co.facturador.mapper.SelectMapper;
import co.facturador.services.ProductoService;
import co.facturador.services.ProductoServiceImpl;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author EderArmando
 */
public class FacturarController implements Initializable{

    TableView<FacturaTableViewDto> tv;
    private Pane paneFactura;
    private ProductoService productoService;
    
    public void crearFactura(Pane paneFactura){
        productoService = new ProductoServiceImpl();
        List<Productos> listaProductos = productoService.allProducts();
        List<SelectItemDto> listSelectProducto = SelectMapper.listoProductosTolistSelectItem(listaProductos);
        final ObservableList<SelectItemDto> products  = FXCollections.observableArrayList(listSelectProducto);
        ObservableList<FacturaTableViewDto> commands  = FXCollections.observableArrayList(new FacturaTableViewDto("1",listSelectProducto.get(0),"fsdf","2","4","")); 

        tv = new TableView<FacturaTableViewDto>();
        tv.setItems(commands);

        TableColumn<FacturaTableViewDto, SelectItemDto> selectProducto = new TableColumn<FacturaTableViewDto, SelectItemDto>("product");
        selectProducto.setMinWidth(140);
        selectProducto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FacturaTableViewDto,SelectItemDto>, ObservableValue<SelectItemDto>>() {

            @Override
            public ObservableValue<SelectItemDto> call(TableColumn.CellDataFeatures<FacturaTableViewDto, SelectItemDto> cdf) {
                try {
                    JavaBeanObjectPropertyBuilder<SelectItemDto> jbdpb = JavaBeanObjectPropertyBuilder.create();
                    jbdpb.bean(cdf.getValue());
                    jbdpb.name("productos");//Este nombre debe ser igual al del objeto que se declara en la clase
                    return (ObservableValue) jbdpb.build();
                } catch (NoSuchMethodException e) {
                    System.err.println(e.getMessage());
                }
                return null;
            }
        });
        
        final StringConverter<SelectItemDto> converter = new StringConverter<SelectItemDto>() {

            @Override
            public String toString(SelectItemDto p) {
                return p.getTexto();
            }

            @Override
        public SelectItemDto fromString(String s) {
                // TODO Auto-generated method stub
                return null;
            }
        };

        selectProducto.setCellFactory(new Callback<TableColumn<FacturaTableViewDto,SelectItemDto>, TableCell<FacturaTableViewDto,SelectItemDto>>() {

            @Override
            public TableCell<FacturaTableViewDto, SelectItemDto> call(TableColumn<FacturaTableViewDto, SelectItemDto> tc) {
                return new ComboBoxTableCell<FacturaTableViewDto, SelectItemDto>(converter, products) {
                    @Override
                    public void updateItem(SelectItemDto product, boolean empty) {
                        super.updateItem(product, empty);
                        if (product != null) {
                            setText(product.getTexto());
                        }
                    }
                };
            }
        });
        
        
        /*-----------------------------------------------------------------------**/
        //Create a customer cell factory so that cells can support editing. 
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
        
        //Lista Codigo Producto
        TableColumn codigoProducto = new TableColumn("Código");
        codigoProducto.setMinWidth( 100 );
        codigoProducto.setCellFactory(cellFactory);
        codigoProducto.setCellValueFactory(new PropertyValueFactory<FacturaTableViewDto,String>("codigoProducto"));
        codigoProducto.setEditable(true);
        codigoProducto.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FacturaTableViewDto,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FacturaTableViewDto, String> event) {
                        ((FacturaTableViewDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
        
        //Descripcion
      TableColumn descripcion = new TableColumn("Descripción");
      descripcion.setMinWidth( 200 );
      descripcion.setCellValueFactory(new PropertyValueFactory<FacturaTableViewDto,String>("descripcion"));
      descripcion.setCellFactory(cellFactory);
      descripcion.setEditable(true);
      descripcion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FacturaTableViewDto,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FacturaTableViewDto, String> event) {
                        ((FacturaTableViewDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDescripcion(event.getNewValue());
                    }
                 }
        );
        
       //Unidades
        TableColumn unidades = new TableColumn("Unidades");
        unidades.setMinWidth( 50 );
        unidades.setCellValueFactory(new PropertyValueFactory<FacturaTableViewDto,String>("unidades"));
        unidades.setCellFactory(cellFactory);
        unidades.setEditable(true);
        unidades.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FacturaTableViewDto,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FacturaTableViewDto, String> event) {
                        ((FacturaTableViewDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setUnidades(event.getNewValue());
                    }
                 }
        );
        TableColumn valorUnitario = new TableColumn("Valor Unitario");
        valorUnitario.setMinWidth( 100 );
        valorUnitario.setCellValueFactory(new PropertyValueFactory<FacturaTableViewDto,String>("valorUnitario"));
        valorUnitario.setCellFactory(cellFactory);
        valorUnitario.setEditable(true);
        valorUnitario.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FacturaTableViewDto,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FacturaTableViewDto, String> event) {
                        ((FacturaTableViewDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValorUnitario(event.getNewValue());
                    }
                 }
        );
        
        TableColumn valorTotal = new TableColumn("Valor Total");
        valorTotal.setCellValueFactory(new PropertyValueFactory<FacturaTableViewDto,String>("valorTotal"));
        valorTotal.setCellFactory(cellFactory);
        valorTotal.setEditable(true);
        valorTotal.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FacturaTableViewDto,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FacturaTableViewDto, String> event) {
                        ((FacturaTableViewDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setValorTotal(event.getNewValue());
                    }
                 }
        );
        tv.getColumns().add(codigoProducto);
        tv.getColumns().add(selectProducto);
        tv.getColumns().add(unidades);
        tv.getColumns().add(valorUnitario);
        tv.getColumns().add(valorTotal);
        
        tv.setEditable(true);
        this.paneFactura = paneFactura;
        this.paneFactura.getChildren().add(tv);
    }
    
    @FXML
    public void imprimirFactura(ActionEvent event) throws JRException, PrinterException{
        List<ItemFactura> detalleFactura = new ArrayList<ItemFactura>();
        ItemFactura item = new ItemFactura();
            item.setCodigoProducto(1);
            item.setDescripcion("product 1");
            item.setUnidades(5);
            item.setValorUnitario(63F);
            item.setValorTotal(485f);
            
            ItemFactura item1 = new ItemFactura();
            item1.setCodigoProducto(1);
            item1.setDescripcion("product 3");
            item1.setUnidades(5);
            item1.setValorUnitario(63F);
            item1.setValorTotal(485f);
            
            detalleFactura.add(item);
            detalleFactura.add(item1);
        
            JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(detalleFactura);
            String reporteJrxml ="C:/Users/EderArmando/Documents/NetBeansProjects/git/colombian/facturador/src/main/resources/jasper/factura.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reporteJrxml);
            Map<String,Object> parametros = new  HashMap<String,Object>();
            parametros.put("usuario", "edeani");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parametros, beanCollectionDataSource);
            
            PrintService selectedService = PrintUtil.findPrintService("EPSON TM-T20 Receipt");
            
            if(selectedService != null)
                {
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
                    printerJob.setPrintService(selectedService);
                    boolean printSucceed = JasperPrintManager.printReport(jasperPrint, false);
                    if(printSucceed){
                        System.out.println("Imprimí");
                    }else{
                        System.out.println("Try again");
                    }
                }
            JasperViewer.viewReport(jasperPrint, false);
            
            
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Cargar factura inicial");
    }
}
