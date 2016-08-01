/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "detallepedido")
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddetallepedido")
    private Integer iddetallepedido;
    @Column(name = "cantidadorden")
    private Integer cantidadorden;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto idproducto;
    @OneToMany(mappedBy = "iddetallepedido")
    private List<Pedido> pedidoList;

    public Detallepedido() {
    }

    public Detallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Integer getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Integer getCantidadorden() {
        return cantidadorden;
    }

    public void setCantidadorden(Integer cantidadorden) {
        this.cantidadorden = cantidadorden;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }
    
}
