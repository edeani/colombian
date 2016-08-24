/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dto;

import java.io.Serializable;

/**
 *
 * @author user
 */

public class ProductoDto implements Serializable{
    
    private Integer idproducto;
    private String nombreproducto;
    private Float precioproducto;

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Float getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(Float precioproducto) {
        this.precioproducto = precioproducto;
    }
    
    
}
