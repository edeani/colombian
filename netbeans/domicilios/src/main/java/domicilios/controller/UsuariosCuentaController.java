/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.PedidoViewDto;
import domicilios.dto.UsuarioDto;
import domicilios.dto.VerPedidoDto;
import domicilios.entidad.Detallepedido;
import domicilios.entidad.Pedido;
import domicilios.entidad.Usuario;
import domicilios.mapper.PedidoClienteDtoMapper;
import domicilios.service.PedidoService;
import domicilios.service.UsuarioService;
import domicilios.service.autorizacion.SecurityService;
import domicilios.util.LectorPropiedades;
import domicilios.util.ManageCookies;
import domicilios.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/user")
public class UsuariosCuentaController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SecurityService securityService;

    private List<String> coordUrbana;

    @Autowired
    private void listaEstructuraUrbana() {
        coordUrbana = new ArrayList<>();
        LectorPropiedades lp = new LectorPropiedades();
        final String listaBuff = lp.leerPropiedad(getPropiedadesColombian(), "coord.urbana");
        String listado[] = listaBuff.split(",");
        coordUrbana.addAll(Arrays.asList(listado));
    }
    
    @GetMapping("/pedidos.htm")
    public ModelAndView misPedidos() {

        Usuario usuario = securityService.getCurrentUser();
        UsuarioDto usuarioDto = usuarioService.findUsuarioByCorreoDto(usuario.getCorreo());
        List<PedidoViewDto> pedidos = pedidoService.findPedidosXPageUsuario(1, 5, usuario.getIdusuario());
        ModelAndView mav = new ModelAndView("pedido/ordenes");

        setBasicModel(mav, usuarioDto);
        String direccion = usuarioDto.getDireccion();
        if (direccion != null) {
            String[] direccionPartes = direccion.split("#");
            String placa = direccionPartes[1];

            mav.addObject("datoComponente1", placa.split("-")[0]);
            mav.addObject("datoComponente2", placa.split("-")[1]);

            String nombreNum = direccionPartes[0];
            String[] nombreNumPartes = nombreNum.split(" ");

            if (nombreNumPartes.length == 2) {
                mav.addObject("componente", nombreNumPartes[0]);
                mav.addObject("datoComponente", nombreNumPartes[1]);
            } else {
                mav.addObject("componente", nombreNumPartes[0] + " " + nombreNumPartes[1]);
                mav.addObject("datoComponente", nombreNumPartes[2]);
            }
        }
        mav.addObject("usuario", usuarioDto);

        mav.addObject("pedidos", pedidos);
        return mav;

    }

    @PostMapping("/ajax/detalle-orden.htm")
    public ModelAndView detallePedido(@RequestParam Long idpedido){
       ModelAndView mav = new ModelAndView("pedido/detalleOrden");
       VerPedidoDto verPedidoDto = new VerPedidoDto();
        //Seteo los productos del pedido
       List<Detallepedido> detallepedidos = pedidoService.listDetallePedido(idpedido);
       verPedidoDto.setProductos(PedidoClienteDtoMapper.listDetallePedidoToProductoClienteDto(detallepedidos));
       
       /**
        * Traigo los datos generales del pedido
        */
       Pedido pedido = pedidoService.findById(idpedido);
       
       verPedidoDto.setIdusuario(pedido.getIdusuario().getIdusuario());
       verPedidoDto.setComentarios(pedido.getComentarios());
       verPedidoDto.setIdpedido(pedido.getIdpedido());
       verPedidoDto.setTotal(pedido.getTotalpedido());
       
       mav.addObject("tipopago", pedido.getIdtipopago().getNombre());
       mav.addObject("fecha", pedido.getFecha());
       mav.addObject("pedido",verPedidoDto);
       return  mav;
    }
    @PostMapping("/ajax/actualizar-usuario.htm")
    public ModelAndView actualizarUsuario(@ModelAttribute @Valid UsuarioDto usuarioDto, BindingResult binding,
            HttpServletResponse response) {
        if (binding.hasErrors()) {
            ManageCookies.setCookie(response, "updateUser", "N", 1, "/");
            ModelAndView mav = new ModelAndView("pedido/datos-cliente");
            setBasicModel(mav, usuarioDto);
            mav.addObject("usuario", usuarioDto);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("pedido/datos-cliente");
            usuarioService.actualizarUsuarioAdministracion(usuarioDto);
            ManageCookies.setCookie(response, "updateUser", "S", 1, "/");
            mav.addObject("mensaje", "OK");
            return mav;
        }
    }
    
    @ModelAttribute("coord")
    public List<String> listaCoordUrbanas() {
        return coordUrbana;
    }
}
