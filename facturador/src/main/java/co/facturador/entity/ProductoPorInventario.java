/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "producto_por_inventario")
public class ProductoPorInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigo_producto")
    private int codigoProducto;
    @Basic(optional = false)
    @Column(name = "codigo_producto_inventario")
    private int codigoProductoInventario;
    @Basic(optional = false)
    @Column(name = "aplica_inventario")
    private Character aplicaInventario;
    @Basic(optional = false)
    @Column(name = "porcentaje")
    private float porcentaje;
    @Basic(optional = false)
    @Column(name = "porcentaje_mesas")
    private float porcentajeMesas;
    @Basic(optional = false)
    @Column(name = "porcentaje_llevar")
    private float porcentajeLlevar;

    public ProductoPorInventario() {
    }

    public ProductoPorInventario(Integer id) {
        this.id = id;
    }

    public ProductoPorInventario(Integer id, int codigoProducto, int codigoProductoInventario, Character aplicaInventario, float porcentaje, float porcentajeMesas, float porcentajeLlevar) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.codigoProductoInventario = codigoProductoInventario;
        this.aplicaInventario = aplicaInventario;
        this.porcentaje = porcentaje;
        this.porcentajeMesas = porcentajeMesas;
        this.porcentajeLlevar = porcentajeLlevar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoProductoInventario() {
        return codigoProductoInventario;
    }

    public void setCodigoProductoInventario(int codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    public Character getAplicaInventario() {
        return aplicaInventario;
    }

    public void setAplicaInventario(Character aplicaInventario) {
        this.aplicaInventario = aplicaInventario;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getPorcentajeMesas() {
        return porcentajeMesas;
    }

    public void setPorcentajeMesas(float porcentajeMesas) {
        this.porcentajeMesas = porcentajeMesas;
    }

    public float getPorcentajeLlevar() {
        return porcentajeLlevar;
    }

    public void setPorcentajeLlevar(float porcentajeLlevar) {
        this.porcentajeLlevar = porcentajeLlevar;
    }
}
