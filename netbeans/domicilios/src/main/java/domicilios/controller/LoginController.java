package domicilios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domicilios.dto.UsuarioRegistroDto;
import domicilios.entidad.Usuario;
import domicilios.service.UsuarioService;
import domicilios.service.autorizacion.SecurityService;
import domicilios.service.mailing.MailsUsuario;
import domicilios.util.LectorPropiedades;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private MailsUsuario mailsUsuario;

    @GetMapping(value = "/signin.htm")
    public ModelAndView signinPage() {
        ModelAndView mav;
        if (securityService.getCurrentUser() == null) {
            mav = new ModelAndView("usuario/signin");
            setBasicModel(mav, new UsuarioRegistroDto());
        } else {
            /**
             *Redirigir a la pantalla de pedidos
             */
            mav = new ModelAndView("redirect:/home.htm");
        }
        return mav;
    }

    @GetMapping(value = "/login.htm")
    public ModelAndView loginDomicilios() {
        return new ModelAndView("login");
    }

    @GetMapping("/403.htm")
    public ModelAndView domicilios403() {
        return  new ModelAndView("403");
    }

    @GetMapping("/logoutSuccessful.htm")
    public ModelAndView domicilioslogout() {
        ModelAndView mav = new ModelAndView("inicio");
        mav.addObject("mensaje", "Deslogueado");
        return mav;
    }

    @GetMapping("/userInfo.htm")
    public ModelAndView domiciliosuser() {
        ModelAndView mav = new ModelAndView("inicio");
        mav.addObject("mensaje", "Ingreso Ok");
        return mav;
    }

    
    @PostMapping(value = "/signin.htm")
    public ModelAndView registrarUsuario(@ModelAttribute @Valid UsuarioRegistroDto usuarioRegistroDto, BindingResult binding) {
        if (binding.hasErrors()) {
            ModelAndView mavError = new ModelAndView("usuario/signin");
            setBasicModel(mavError, usuarioRegistroDto);
            return mavError;
        } else {
            Usuario usuario = new Usuario();
            LectorPropiedades lp = new LectorPropiedades();
            usuario.setEstado(lp.leerPropiedad(getPropiedadesColombian(), "usuario.estadoregistro"));
            usuario.setCorreo(usuarioRegistroDto.getCorreo());
            usuario.setPassword(usuarioRegistroDto.getPassword());
            usuario.setNombreusuario(usuarioRegistroDto.getNombre());
            try {
                usuarioService.crearUsuario(usuario);
                mailsUsuario.mailRegistro(usuario);
                return new ModelAndView("redirect:/alerta/mail.htm");
            } catch (Exception e) {
                ModelAndView mav = new ModelAndView("usuario/signin");
                mav.addObject("error_crear", "Error creando Usuario");
                return mav;
            }

        }
    }

    @GetMapping("/activar/usuario.htm")
    public ModelAndView activarUsuario(@RequestParam String token, @RequestParam String email) {
        try {
            securityService.autenticarUsuarioRegistrado(email, token);
            return new ModelAndView("redirect:/wellcome.htm");
        } catch (Exception e) {
            if (e.getMessage().equals("1003")) {
                return new ModelAndView("redirect:/token.htm");
            } else {
                return new ModelAndView("redirect:/403.htm");
            }
        }
    }

    @GetMapping("/wellcome.htm")
    public ModelAndView paginaBienvenido() {
        return new ModelAndView("wellcome");
    }
    
    @GetMapping("/token.htm")
    public ModelAndView paginaTokenInvalido() {
        return new ModelAndView("token");
    }

    @GetMapping("/alerta/mail.htm")
    public ModelAndView alertaMail() {
        return new ModelAndView("mensaje_activarmail");
    }
}
