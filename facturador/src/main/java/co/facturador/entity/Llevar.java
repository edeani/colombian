/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "llevar")
public class Llevar implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LlevarPK llevarPK;
    @Basic(optional = false)
    @Column(name = "estado_orden")
    private Character estadoOrden;
    @Basic(optional = false)
    @Column(name = "pago_orden")
    private float pagoOrden;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "numero_mesa")
    private Integer numeroMesa;
    @Basic(optional = false)
    @Column(name = "valor_total")
    private float valorTotal;
    @Basic(optional = false)
    @Column(name = "codigo_mesera")
    private int codigoMesera;
    @Column(name = "id_usuario")
    private String idUsuario;
    @Basic(optional = false)
    @Column(name = "pago_tarjeta")
    private float pagoTarjeta;
    @Basic(optional = false)
    @Column(name = "valor_bruto")
    private float valorBruto;
    @Basic(optional = false)
    @Column(name = "id_imp")
    private float idImp;
    @Basic(optional = false)
    @Column(name = "valor_impuesto")
    private float valorImpuesto;
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;

    public Llevar() {
    }

    public Llevar(LlevarPK llevarPK) {
        this.llevarPK = llevarPK;
    }

    public Llevar(LlevarPK llevarPK, Character estadoOrden, float pagoOrden, float valorTotal, int codigoMesera, float pagoTarjeta, float valorBruto, float idImp, float valorImpuesto) {
        this.llevarPK = llevarPK;
        this.estadoOrden = estadoOrden;
        this.pagoOrden = pagoOrden;
        this.valorTotal = valorTotal;
        this.codigoMesera = codigoMesera;
        this.pagoTarjeta = pagoTarjeta;
        this.valorBruto = valorBruto;
        this.idImp = idImp;
        this.valorImpuesto = valorImpuesto;
    }

    public Llevar(int numeroOrden, Date fechaOrden) {
        this.llevarPK = new LlevarPK(numeroOrden, fechaOrden);
    }

    public LlevarPK getLlevarPK() {
        return llevarPK;
    }

    public void setLlevarPK(LlevarPK llevarPK) {
        this.llevarPK = llevarPK;
    }

    public Character getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(Character estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public float getPagoOrden() {
        return pagoOrden;
    }

    public void setPagoOrden(float pagoOrden) {
        this.pagoOrden = pagoOrden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCodigoMesera() {
        return codigoMesera;
    }

    public void setCodigoMesera(int codigoMesera) {
        this.codigoMesera = codigoMesera;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getPagoTarjeta() {
        return pagoTarjeta;
    }

    public void setPagoTarjeta(float pagoTarjeta) {
        this.pagoTarjeta = pagoTarjeta;
    }

    public float getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(float valorBruto) {
        this.valorBruto = valorBruto;
    }

    public float getIdImp() {
        return idImp;
    }

    public void setIdImp(float idImp) {
        this.idImp = idImp;
    }

    public float getValorImpuesto() {
        return valorImpuesto;
    }

    public void setValorImpuesto(float valorImpuesto) {
        this.valorImpuesto = valorImpuesto;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
