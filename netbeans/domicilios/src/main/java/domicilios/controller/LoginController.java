package domicilios.controller;

import domicilios.dto.UsuarioRegistroDto;
import domicilios.entidad.Usuario;
import domicilios.service.UsuarioService;
import domicilios.service.autorizacion.SecurityService;
import domicilios.util.LectorPropiedades;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/signin.htm", method = RequestMethod.GET)
    public ModelAndView signinPage() {
        ModelAndView mav = null;
        if (securityService.getCurrentUser()==null) {
            mav = new ModelAndView("usuario/signin");
            setBasicModel(mav, new UsuarioRegistroDto());
        } else {
            /**
             * TODO: Redirigir a la pantalla de pedidos
             */
            mav = new ModelAndView("redirect:/home.htm");
        }
        return mav;
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public ModelAndView loginDomicilios() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/403.htm")
    public ModelAndView domicilios403() {
        ModelAndView mav = new ModelAndView("403");
        return mav;
    }

    @RequestMapping("/logoutSuccessful.htm")
    public ModelAndView domicilioslogout() {
        ModelAndView mav = new ModelAndView("inicio");
        mav.addObject("mensaje", "Deslogueado");
        return mav;
    }

    @RequestMapping("/userInfo.htm")
    public ModelAndView domiciliosuser() {
        ModelAndView mav = new ModelAndView("inicio");
        mav.addObject("mensaje", "Ingreso Ok");
        return mav;
    }

    @SuppressWarnings("warnings-registrarusuario")
    @RequestMapping(value = "/signin.htm", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute @Valid UsuarioRegistroDto usuarioRegistroDto, BindingResult binding) {
        if (binding.hasErrors()) {
            ModelAndView mavError = new ModelAndView("usuario/signin");
            setBasicModel(mavError, usuarioRegistroDto);
            return mavError;
        } else {
            Usuario usuario = new Usuario();
            LectorPropiedades lp = new LectorPropiedades();
            usuario.setEstado(lp.leerPropiedad(getPROPIEDADES_COLOMBIAN(),"usuario.estadoregistro"));
            usuario.setCorreo(usuarioRegistroDto.getCorreo());
            usuario.setPassword(usuarioRegistroDto.getPassword());
            usuario.setNombreusuario(usuarioRegistroDto.getNombre());
            try {
                usuarioService.crearUsuario(usuario);
                return new ModelAndView("redirect:/contenido/products.htm");
            } catch (Exception e) {
                ModelAndView mav = new ModelAndView("redirect:/registrar.htm");
                mav.addObject("error_crear", "Error creando Usuario");
                return mav;
            }

        }
    }

    @RequestMapping("/activar/usuario.htm")
    public ModelAndView activarUsuario(@RequestParam String token,@RequestParam String email) {
        try {
            securityService.autenticarUsuarioRegistrado(email, token);
            return new ModelAndView("redirect:/contenido/products.htm");
        } catch (Exception e) {
            return new ModelAndView("redirect:/403.htm");
        }
    }

}
