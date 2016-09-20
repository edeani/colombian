/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dto;

/**
 *
 * @author user
 */
public class ProductoClienteDto {
    private Integer idproducto;
    private String nombreproducto;
    private Float precio;
    private Integer cantidad;
    private Float total;

    public ProductoClienteDto(Integer idproducto,String nombreProducto,Float precio,Integer cantidad,Float total){
        this.idproducto = idproducto;
        this.nombreproducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }
    
    
}
