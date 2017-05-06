/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.TreeBean;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.mapper.GastosMapper;
import com.mycompany.reportes.GastosService;
import com.mycompany.reportes.GastosServiceImpl;
import com.mycompany.util.Formatos;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author joseefren
 */
@ManagedBean(name = "gastosBean")
@RequestScoped
public class GastosBean implements Serializable{

    /**
     * Creates a new instance of GastosBean
     */
    private List<GastosMapper> gastos1;
    private List<GastosMapper> gastos2;
    private List<GastosMapper> gastos3;
    private String total;
    private Date fi;
    private Date ff;
    private GastosService gastosService;
    private TreeNode root;
    public GastosBean() {
        fi = new Date();
        ff = new Date();
        gastosService = new GastosServiceImpl();
        root = new DefaultTreeNode("Root", null);
        total = "0";
    }

    public void reporteGastos() {
        Formatos formato = new Formatos();
        gastosService.gastos(getFi(), getFf());
        setTotal(formato.numeroToStringFormato(gastosService.getTotal()));
        setGastos1(gastosService.getGastosNivel1());
        setGastos2(gastosService.getGastosNivel2());
        setGastos3(gastosService.getGastosNivel3());
        
        contruirArbol();
    }

    public void contruirArbol() {

        TreeNode arrNode[] = new TreeNode[gastos1.size()];
        TreeNode arr2Node[] = new TreeNode[gastos2.size()];
        TreeNode arr3Node[] = new TreeNode[gastos3.size()];
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < gastos1.size(); i++) {
            arrNode[i] = new DefaultTreeNode(gastos1.get(i), getRoot());
            for (int i2 = n2; i2 < gastos2.size(); i2++) {
                if (gastos2.get(i2).getPadre().equals(gastos1.get(i).getCodigo())) {
                    arr2Node[i2] = new DefaultTreeNode(gastos2.get(i2), arrNode[i]);
                    for (int i3 = n3; i3 < gastos3.size(); i3++) {
                        if (gastos3.get(i3).getPadre().equals(gastos2.get(i2).getCodigo())) {
                            arr3Node[i3] = new DefaultTreeNode(gastos3.get(i3), arr2Node[i2]);
                        }else{
                          n3=i3;
                          break;
                        }
                    }
                } else {
                    n2 = i2;
                    break;
                }
            }
        }
    }
    public void navegarReporteGastos() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/gastos");
    }

    /**
     * @return the gastos1
     */
    public List<GastosMapper> getGastos1() {
        return gastos1;
    }

    /**
     * @param gastos1 the gastos1 to set
     */
    public void setGastos1(List<GastosMapper> gastos1) {
        this.gastos1 = gastos1;
    }

    /**
     * @return the gastos2
     */
    public List<GastosMapper> getGastos2() {
        return gastos2;
    }

    /**
     * @param gastos2 the gastos2 to set
     */
    public void setGastos2(List<GastosMapper> gastos2) {
        this.gastos2 = gastos2;
    }

    /**
     * @return the gastos3
     */
    public List<GastosMapper> getGastos3() {
        return gastos3;
    }

    /**
     * @param gastos3 the gastos3 to set
     */
    public void setGastos3(List<GastosMapper> gastos3) {
        this.gastos3 = gastos3;
    }

    /**
     * @return the fi
     */
    public Date getFi() {
        return fi;
    }

    /**
     * @param fi the fi to set
     */
    public void setFi(Date fi) {
        this.fi = fi;
    }

    /**
     * @return the ff
     */
    public Date getFf() {
        return ff;
    }

    /**
     * @param ff the ff to set
     */
    public void setFf(Date ff) {
        this.ff = ff;
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }
}
