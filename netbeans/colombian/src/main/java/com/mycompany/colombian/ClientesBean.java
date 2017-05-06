/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.entidades.Barrios;
import com.mycompany.entidades.Clientes;
import com.mycompany.reportes.BarriosService;
import com.mycompany.reportes.BarriosServiceImpl;
import com.mycompany.reportes.ClientesService;
import com.mycompany.reportes.ClientesServiceImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author user
 */
@ManagedBean(name = "clientesBean")
@ViewScoped
public class ClientesBean implements Serializable{

    /**
     * Creates a new instance of ClientesBean
     */
    private LazyDataModel<Clientes> lazyModel;
    private List<Clientes> clientes;
    private List<Barrios> barrios;
    private Barrios barrio;
    private ClientesService clienteService;
    private Long b;
    private int count;
    private List<SelectItem> bars;

    public ClientesBean() {
        bars = new LinkedList<SelectItem>();
        clienteService = new ClientesServiceImpl();
        clientes = clienteService.listarClientes();
        if(clientes==null)
        setCount(0);
        else
        setCount(clientes.size());
        
        barrio = new Barrios();
        cargarBarrios();
        llenarItemsBarrios();
        
    }

    public void llenarItemsBarrios() {
        if(barrios!=null){
        for (Iterator<Barrios> it = barrios.iterator(); it.hasNext();) {
            Barrios barrios1 = it.next();
            getBars().add(new SelectItem(barrios1.getCodigoBarrio(), barrios1.getDescripcionBarrio()));
        }
        }
    }

    public void navegarReporteClientes() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/clientes");
    }

   

    public String reporteClientesBarrio() {

        //setClientes(clienteService.listaClienteBarrio(barrio));
        if (b == 0) {
            setClientes(clienteService.listarClientes());
        } else {
            setClientes(clienteService.listaClienteBarrio(b));
        }

        setCount(clientes.size());
        return "";
    }

    public void cargarBarrios() {
        BarriosService barriosService = new BarriosServiceImpl();
        barrios = barriosService.traerBarrios();
    }

    public LazyDataModel<Clientes> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<Clientes>() {
                @Override
                public List<Clientes> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    int start = first;
                    int end = first + pageSize;

                    List<Clientes> listaClientes = clienteService.listarClientes();
                    this.setRowCount(listaClientes.size());
                    return listaClientes;
                }

                @Override
                public void setRowIndex(int rowIndex) {
                    /*
                     * The following is in ancestor (LazyDataModel):
                     * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                     */
                    if (rowIndex == -1 || getPageSize() == 0) {
                        super.setRowIndex(-1);
                    } else {
                        super.setRowIndex(rowIndex % getPageSize());
                    }
                }
            };
        }

        return lazyModel;
    }

    /**
     * @return the listaClientes
     */
    public List<Clientes> getListaClientes() {
        return getClientes();
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<Clientes> clientes) {
        this.setClientes(clientes);
    }

    /**
     * @return the barrios
     */
    public List<Barrios> getBarrios() {
        return barrios;
    }

    /**
     * @param barrios the barrios to set
     */
    public void setBarrios(List<Barrios> barrios) {
        this.barrios = barrios;
    }

    /**
     * @return the barrio
     */
    public Barrios getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrios barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the clientes
     */
    public List<Clientes> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the b
     */
    public Long getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Long b) {
        this.b = b;
    }

    /**
     * @return the bars
     */
    public List<SelectItem> getBars() {
        return bars;
    }

    /**
     * @param bars the bars to set
     */
    public void setBars(List<SelectItem> bars) {
        this.bars = bars;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
}
