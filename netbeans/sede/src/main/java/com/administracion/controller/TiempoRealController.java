/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.ItemsHashDTO;
import com.administracion.dto.TiempoRealSedeDto;
import com.administracion.enumeration.EstadosEnum;
import com.administracion.service.ClasePagoService;
import com.administracion.service.jsf.CierreColombianService;
import com.administracion.util.Formatos;
import com.mycompany.dto.TiempoRealDto;
import com.mycompany.enums.EnumTipoPagoTarjeta;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/tiemporeal")
public class TiempoRealController extends BaseController {

    @Autowired
    private CierreColombianService cierreColombianService;

    @Autowired
    private ClasePagoService clasePagoService;

    @RequestMapping("/cierres.htm")
    public ModelAndView pagina() {
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/tiempoReal");
        return mav;
    }

    @RequestMapping("/ajax/calcular.htm")
    public ModelAndView calcularTiempoReal(@RequestParam String fecha, @PathVariable String sede, @RequestParam(required = false, value = "sede") String subsede) {
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/datosTiempoReal");
        
        TiempoRealDto cierreDiario = new TiempoRealDto();
        cierreDiario.setVentas(cierreColombianService.cierreVentas(Formatos.StringDateToDate(fecha), subsede));
        cierreDiario.setGastos(cierreColombianService.cierreGastos(Formatos.StringDateToDate(fecha), subsede));
        cierreDiario.setConsignaciones(cierreColombianService.cierreConsignaciones(Formatos.StringDateToDate(fecha), subsede));
        cierreDiario.setListaConsignaciones(cierreColombianService.cierreListaConsignaciones(Formatos.StringDateToDate(fecha), subsede));

        TiempoRealSedeDto tiempoRealSede = new TiempoRealSedeDto();
        List<ItemsHashDTO> itemsCierre = cierreColombianService.cierreRealData(Formatos.StringDateToDate(fecha), subsede);

        for (ItemsHashDTO itemsHashDTO : itemsCierre) {
            if (EnumTipoPagoTarjeta.NEQUI.getName().equals(itemsHashDTO.getName())) {
                tiempoRealSede.setPagosNequi(itemsHashDTO.getValue());
                cierreDiario.setPagosNequi(tiempoRealSede.getPagosNequi());
            } else if (EnumTipoPagoTarjeta.DAVIPLATA.getName().equals(itemsHashDTO.getName())) {
                tiempoRealSede.setPagosDaviplata(itemsHashDTO.getValue());
                cierreDiario.setPagosDaviplata(tiempoRealSede.getPagosDaviplata());
            } else if (EnumTipoPagoTarjeta.TRANSFERENCIA.getName().equals(itemsHashDTO.getName())) {
                tiempoRealSede.setPagosTransferencias(itemsHashDTO.getValue());
                cierreDiario.setPagosTransferencias(tiempoRealSede.getPagosTransferencias());
            } else if ("Caja Inicial".equals(itemsHashDTO.getName())) {
                Double val1 = itemsHashDTO.getValue() == null ? 0D : itemsHashDTO.getValue();
                tiempoRealSede.setCajaInicial(val1);
                cierreDiario.setCajaInicial(val1);
            }
        }

        cierreDiario.setCajaInicial(tiempoRealSede.getCajaInicial());
        
        if(clasePagoService.findClasePagoById(3, subsede).getEstado().equals(EstadosEnum.Activo.getEstado())){
            cierreDiario.setPropinas(cierreColombianService.propinasDiario(Formatos.StringDateToDate(fecha), subsede));
            mav.addObject("fieldPropinas", EstadosEnum.Activo.getEstado());
        }else{
            mav.addObject("fieldPropinas", EstadosEnum.Inactivo.getEstado());
        }

        boolean paymentOne = false;
        if (clasePagoService.findClasePagoById(1, subsede).getEstado().equals(EstadosEnum.Activo.getEstado())) {
            cierreDiario.setPagosTarjetas(cierreColombianService.cierrePagosConTarjetas(Formatos.StringDateToDate(fecha), subsede));
            if (Objects.nonNull(tiempoRealSede)) {
                Double totalPagosTarjetas = cierreDiario.getPagosTarjetas() - tiempoRealSede.getPagosNequi()
                        - tiempoRealSede.getPagosDaviplata() - tiempoRealSede.getPagosTransferencias();
                cierreDiario.setPagosTarjetas(totalPagosTarjetas);
            }
            paymentOne = true;
            //cierreDiario.setCajaFinal(cierreDiario.getCajaFinal() - cierreDiario.getPagosTarjetas());

        }

        if (clasePagoService.findClasePagoById(2, subsede).getEstado().equals(EstadosEnum.Activo.getEstado())) {
            cierreDiario.setDescuentos(cierreColombianService.cierreDescuentos(Formatos.StringDateToDate(fecha), subsede));
            //cierreDiario.setCajaFinal(cierreDiario.getCajaFinal() - cierreDiario.getDescuentos()    );
        }

        //System.out.println(cierreDiario.getVentas() + cierreDiario.getCajaInicial() - cierreDiario.getConsignaciones() - cierreDiario.getGastos());
        checkValuesCajaFinal(cierreDiario);
        if (paymentOne) {
            cierreDiario.setCajaFinal(cierreDiario.getVentas() + cierreDiario.getCajaInicial() + cierreDiario.getPropinas() - cierreDiario.getConsignaciones() - cierreDiario.getGastos()
                    - cierreDiario.getDescuentos() - cierreDiario.getPagosTarjetas() - tiempoRealSede.getPagosNequi()
                    - tiempoRealSede.getPagosDaviplata() - tiempoRealSede.getPagosTransferencias());
        } else {
            cierreDiario.setCajaFinal(cierreDiario.getVentas() + cierreDiario.getCajaInicial() + cierreDiario.getPropinas() - cierreDiario.getConsignaciones() - cierreDiario.getGastos()
                    - cierreDiario.getPagosNequi() - cierreDiario.getPagosDaviplata() - cierreDiario.getPagosTransferencias() - cierreDiario.getDescuentos() - cierreDiario.getPagosTarjetas());
        }

        mav.addObject("cierreDiario", cierreDiario);
        return mav;
    }

    private void checkValuesCajaFinal(TiempoRealDto cierreDiario) {
        final Double ZERO = 0D;
        if (Objects.isNull(cierreDiario.getVentas())) {
            cierreDiario.setVentas(ZERO);
        }

        if (Objects.isNull(cierreDiario.getConsignaciones())) {
            cierreDiario.setConsignaciones(ZERO);
        }

        if (Objects.isNull(cierreDiario.getCajaInicial())) {
            cierreDiario.setCajaInicial(ZERO);
        }

        if (Objects.isNull(cierreDiario.getGastos())) {
            cierreDiario.setGastos(ZERO);
        }

        if (Objects.isNull(cierreDiario.getPagosNequi())) {
            cierreDiario.setPagosNequi(ZERO);
        }

        if (Objects.isNull(cierreDiario.getPagosDaviplata())) {
            cierreDiario.setPagosDaviplata(ZERO);
        }

        if (Objects.isNull(cierreDiario.getPagosTarjetas())) {
            cierreDiario.setPagosTarjetas(ZERO);
        }

        if (Objects.isNull(cierreDiario.getPagosTransferencias())) {
            cierreDiario.setPagosTransferencias(ZERO);
        }

        if (Objects.isNull(cierreDiario.getPropinas())) {
            cierreDiario.setPropinas(ZERO);
        }

    }

}
