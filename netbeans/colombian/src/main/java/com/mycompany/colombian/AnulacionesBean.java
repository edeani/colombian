/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dto.ItemsDTO;
import com.mycompany.enums.EnumTipoOrden;
import com.mycompany.reportes.DomiciliosDiaService;
import com.mycompany.reportes.DomiciliosDiaServiceImpl;
import com.mycompany.reportes.MesasyllevarService;
import com.mycompany.reportes.MesasyllevarServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author edeani
 */
@ManagedBean(name = "anulacionesBean")
@SessionScoped
public class AnulacionesBean {

    private Long idOrden;
    private List<ItemsDTO> tiposOrden;
    private Long tipoSeleccionado;
    private String mensaje = "";

    /**
     * Creates a new instance of AnulacionesBean
     */
    public AnulacionesBean() {
        /**
         * Llenar select de tipo de orden
         */
        tiposOrden = new ArrayList<ItemsDTO>();

        ItemsDTO itemSelect = new ItemsDTO();
        itemSelect.setId(0L);
        itemSelect.setLabel("Seleccionar");

        tiposOrden.add(itemSelect);

        itemSelect = new ItemsDTO();
        itemSelect.setId(EnumTipoOrden.MESAS.getId().longValue());
        itemSelect.setLabel(EnumTipoOrden.MESAS.getTipo());

        tiposOrden.add(itemSelect);

        itemSelect = new ItemsDTO();
        itemSelect.setId(EnumTipoOrden.LLEVAR.getId().longValue());
        itemSelect.setLabel(EnumTipoOrden.LLEVAR.getTipo());

        tiposOrden.add(itemSelect);

        itemSelect = new ItemsDTO();
        itemSelect.setId(EnumTipoOrden.DOMICILIOS.getId().longValue());
        itemSelect.setLabel(EnumTipoOrden.DOMICILIOS.getTipo());

        tiposOrden.add(itemSelect);

    }

    public void anularOrden() {

        switch (tipoSeleccionado.intValue()) {
            case 1:
                MesasyllevarService mesasyllevarService = new MesasyllevarServiceImpl();
                mesasyllevarService.anularMesa(idOrden);
                this.mensaje = "";
                break;
            case 2:
                MesasyllevarService mesasyllevarService1 = new MesasyllevarServiceImpl();
                mesasyllevarService1.anularLlevar(idOrden);
                this.mensaje = "";
                break;
            case 3:
                DomiciliosDiaService domiciliosDiaService = new DomiciliosDiaServiceImpl();
                domiciliosDiaService.anularDomicilio(idOrden);
                this.mensaje = "";
                break;
            default:
                this.mensaje = "Opción inválida";
                break;
                
        }
        
        this.idOrden = null;
        this.tipoSeleccionado=0L;
    }

    public void navegarAnulaciones() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/anulaciones");
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public List<ItemsDTO> getTiposOrden() {
        return tiposOrden;
    }

    public void setTiposOrden(List<ItemsDTO> tiposOrden) {
        this.tiposOrden = tiposOrden;
    }

    public Long getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(Long tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
