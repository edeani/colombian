/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers;

import co.facturador.dto.FacturaNormalDto;
import co.facturador.controllers.util.EditingCell;
import co.facturador.dto.ItemFactura;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author EderArmando
 */
public class FacturarController implements Initializable{

    @FXML
    private TableView<FacturaNormalDto> tFactura;
    
    @FXML
    private TableColumn codigoProducto;
    
    @FXML
    private TableColumn descripcion;
    
    @FXML
    private TableColumn unidades;
    
    @FXML
    private TableColumn valorUnitario;
    
    @FXML
    private TableColumn valorTotal;
    
    ObservableList<FacturaNormalDto> detalleFactura = FXCollections.observableArrayList(
     new FacturaNormalDto(" ","" , "", "", "")
    );
    
    private Pane paneFactura;
    
    public void crearFactura(Pane paneFactura){
        this.paneFactura = paneFactura;
        //Inicializo la tabla
        tFactura = new TableView();
        tFactura.setEditable(true);
        //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
        //Creo las columnas
        //Codigo Producto
        codigoProducto = new TableColumn("Código");
        codigoProducto.setMinWidth( 100 );
        codigoProducto.setCellFactory(cellFactory);
        codigoProducto.setCellValueFactory(new PropertyValueFactory<FacturaNormalDto,String>("codigoProducto"));
        codigoProducto.setEditable(true);
        codigoProducto.setOnEditCommit(
                 new EventHandler<CellEditEvent<FacturaNormalDto,String>>() {
                    @Override
                    public void handle(CellEditEvent<FacturaNormalDto, String> event) {
                        ((FacturaNormalDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
      //Descripcion
      descripcion = new TableColumn("Descripción");
      descripcion.setMinWidth( 200 );
      descripcion.setCellValueFactory(new PropertyValueFactory<FacturaNormalDto,String>("descripcion"));
      descripcion.setCellFactory(cellFactory);
      descripcion.setEditable(true);
      descripcion.setOnEditCommit(
                 new EventHandler<CellEditEvent<FacturaNormalDto,String>>() {
                    @Override
                    public void handle(CellEditEvent<FacturaNormalDto, String> event) {
                        ((FacturaNormalDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
      //Unidades
        unidades = new TableColumn("Unidades");
        unidades.setMinWidth( 50 );
        unidades.setCellValueFactory(new PropertyValueFactory<FacturaNormalDto,String>("unidades"));
        unidades.setCellFactory(cellFactory);
        unidades.setEditable(true);
        unidades.setOnEditCommit(
                 new EventHandler<CellEditEvent<FacturaNormalDto,String>>() {
                    @Override
                    public void handle(CellEditEvent<FacturaNormalDto, String> event) {
                        ((FacturaNormalDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
        valorUnitario = new TableColumn("Valor Unitario");
        valorUnitario.setMinWidth( 100 );
        valorUnitario.setCellValueFactory(new PropertyValueFactory<FacturaNormalDto,String>("valorUnitario"));
        valorUnitario.setCellFactory(cellFactory);
        valorUnitario.setEditable(true);
        valorUnitario.setOnEditCommit(
                 new EventHandler<CellEditEvent<FacturaNormalDto,String>>() {
                    @Override
                    public void handle(CellEditEvent<FacturaNormalDto, String> event) {
                        ((FacturaNormalDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
        
        valorTotal = new TableColumn("Valor Total");
        valorTotal.setCellValueFactory(new PropertyValueFactory<FacturaNormalDto,String>("valorTotal"));
        valorTotal.setCellFactory(cellFactory);
        valorTotal.setEditable(true);
        valorTotal.setOnEditCommit(
                 new EventHandler<CellEditEvent<FacturaNormalDto,String>>() {
                    @Override
                    public void handle(CellEditEvent<FacturaNormalDto, String> event) {
                        ((FacturaNormalDto) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCodigoProducto(event.getNewValue());
                    }
                 }
        );
        //Agrego las columnas
        tFactura.getColumns().add(codigoProducto);
        tFactura.getColumns().add(descripcion);
        tFactura.getColumns().add(unidades);
        tFactura.getColumns().add(valorUnitario);
        tFactura.getColumns().add(valorTotal);
                
        
        
        tFactura.setItems(detalleFactura);
        tFactura.setLayoutX(100);
        tFactura.setLayoutY(100);
        /*
        TableColumn cNombreProducto = new TableColumn("Descripci&oacute;n");
        TableColumn cUnidades = new TableColumn("Unidades");
        TableColumn cValorUnitario = new TableColumn("Valor unitario");
        TableColumn cValorTotal = new TableColumn("Valor Total");*/
        
        this.paneFactura.getChildren().add(tFactura);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        System.out.println("Ingreso Tabla");
    }
    
    
}
