/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers;

import co.facturador.controllers.util.ComboBoxAuto;
import co.facturador.controllers.util.PrintUtil;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
public class FacturarController implements Initializable {

    private Pane paneFactura;
    private ProductoService productoService;
    private Integer cantidad = 1;
    ObservableList<SelectItemDto> listObservableProducts;

    public void crearFactura(Pane paneFactura) {
        productoService = new ProductoServiceImpl();
        List<Productos> listaProductos = productoService.allProducts();
        List<SelectItemDto> listSelectProducto = SelectMapper.listoProductosTolistSelectItem(listaProductos);
        listObservableProducts = FXCollections.observableArrayList(listSelectProducto);

        GridPane grid = new GridPane();
        grid.setMaxSize(800, 600);
        grid.setPadding(new Insets(30, 0, 30, 0));
        grid.setGridLinesVisible(true);
        Label numero = new Label("Número");
        numero.setPadding(new Insets(5));
        Label labelSelectProducto = new Label("Código");
        labelSelectProducto.setPadding(new Insets(5));
        Label labelProducto = new Label("Producto");
        labelProducto.setPadding(new Insets(5));
        Label labelValorUnitario = new Label("Valor Unitario");
        labelValorUnitario.setPadding(new Insets(5));
        Label labelCantidad = new Label("Cantidad");
        labelCantidad.setPadding(new Insets(5));
        Label labelValorTotal = new Label("Valor Total");
        labelValorTotal.setPadding(new Insets(5));
        Label labelBotones = new Label("");

        //Seteo los labels
        grid.add(numero, 0, 0);
        grid.add(labelSelectProducto, 1, 0);
        grid.add(labelProducto, 2, 0);
        grid.add(labelValorUnitario, 3, 0);
        grid.add(labelCantidad, 4, 0);
        grid.add(labelValorTotal, 5, 0);
        grid.add(labelBotones, 6, 0);

        addRowGridFactura(grid);
        //Seteo la Primera fila
        this.paneFactura = paneFactura;
        this.paneFactura.getChildren().add(grid);
    }

    //Agrego una fila al grid
    public void addRowGridFactura(final GridPane grid) {
        
        Text numero = new Text(cantidad.toString());

        final ComboBox codigoProducto = new ComboBox(listObservableProducts);
        final ComboBox listaProducto = new ComboBox(listObservableProducts);
        final TextField valorUnitario = new TextField();
        final TextField unidades = new TextField();
        valorUnitario.setEditable(false);
        valorUnitario.setMaxSize(100, 30);

        final ComboBoxAuto comboBoxAutoCodigo = new ComboBoxAuto(codigoProducto, "numero");
        codigoProducto.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    unidades.requestFocus();
                    System.out.println("TRUE");
                } else {
                    System.out.println("FALSE");
                }
            }

        });
        codigoProducto.valueProperty().addListener(new ChangeListener<SelectItemDto>() {
            @Override
            public void changed(ObservableValue<? extends SelectItemDto> observable, SelectItemDto oldValue, SelectItemDto newValue) {
                if (newValue != null) {
                    listaProducto.getEditor().setText(newValue.getTexto());
                    valorUnitario.setText(newValue.getPrecio().toString());
                }
            }
        });

        /*codigoProducto.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if(comboBoxAutoCodigo.getEnterSelectProduct())
                unidades.requestFocus();
            }
        });*/
        codigoProducto.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() != -1) {
                    if(unidades.getText()!=null){
                     if(!unidades.getText().equals("")){
                         
                     }
                    }
                }
            }
        });
        codigoProducto.setMaxSize(100, 30);

        ComboBoxAuto comboBoxAutolistaProductos = new ComboBoxAuto(listaProducto, "cadena");
        listaProducto.valueProperty().addListener(new ChangeListener<SelectItemDto>() {
            @Override
            public void changed(ObservableValue<? extends SelectItemDto> observable, SelectItemDto oldValue, SelectItemDto newValue) {
                if (newValue != null) {
                    codigoProducto.getEditor().setText(newValue.getId().toString());
                    valorUnitario.setText(newValue.getPrecio().toString());
                }
            }
        });
        listaProducto.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    unidades.requestFocus();
                }
            }

        });
        /*listaProducto.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                unidades.requestFocus();
            }
        });*/
        listaProducto.setMaxSize(250, 30);

        final TextField valorTotal = new TextField();
        unidades.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                String cadena = "";
                //Logica para quitar caracteres
                if (ke.getCode() == KeyCode.DELETE || ke.getCode() == (KeyCode.BACK_SPACE)) {
                    cadena = unidades.getText() + ke.getText();
                    cadena = cadena.substring(0, cadena.length() - 1);
                } if(ke.getCode() == KeyCode.ENTER){
                    addRowGridFactura(grid);
                    return;
                }else {
                    //Logica para agregar caracteres
                    cadena = unidades.getText() + ke.getText();
                }

                Long vf = 0L;
                if (!cadena.equals("")) {
                    vf = Long.valueOf(cadena);
                }
                Long valor = vf * Long.valueOf(valorUnitario.getText());
                valorTotal.setText(valor.toString());
            }

        });

        unidades.setMaxSize(100, 30);
        valorTotal.setMaxSize(100, 30);
        valorTotal.setEditable(false);

        final Button borrar = new Button("Borrar");
        borrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer index = GridPane.getRowIndex(borrar);
                boolean remove = grid.getChildren().remove(index);
            }
            
        });
        borrar.setMaxSize(100, 30);
        
        grid.add(numero, 0, cantidad);
        grid.add(codigoProducto, 1, cantidad);
        grid.add(listaProducto, 2, cantidad);
        grid.add(valorUnitario, 3, cantidad);
        grid.add(unidades, 4, cantidad);
        grid.add(valorTotal, 5, cantidad);
        grid.add(borrar, 6, cantidad);
        
        cantidad++;
    }

    @FXML
    public void imprimirFactura(ActionEvent event) throws JRException, PrinterException {
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
        String reporteJrxml = "C:/Users/EderArmando/Documents/NetBeansProjects/git/colombian/facturador/src/main/resources/jasper/factura.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(reporteJrxml);
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("usuario", "edeani");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parametros, beanCollectionDataSource);

        PrintService selectedService = PrintUtil.findPrintService("EPSON TM-T20 Receipt");

        if (selectedService != null) {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            printerJob.setPrintService(selectedService);
            boolean printSucceed = JasperPrintManager.printReport(jasperPrint, false);
            if (printSucceed) {
                System.out.println("Imprimí");
            } else {
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
