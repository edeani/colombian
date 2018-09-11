/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.colombian.cali.colombiancaliycali.services.SedesService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author edeani
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    @Autowired
    private SedesService sedeService;
    private JdbcTemplate jdbcTemplate;
    private LectorPropiedades propiedades;
    
    @RequestMapping(value = "/index.htm")
    public ModelAndView inicio(@RequestParam(required = false) String error, HttpServletRequest request,HttpSession session) {
              
        ModelAndView model = new ModelAndView("index");
        model.addObject("error", error);
        /*this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(propiedades.leerPropiedad()));
        List<String> valor = this.jdbcTemplate.queryForList(caliycaliDao.selectJdbTemplate("sede", "sedes", "idsedes="+sede.getIdsedes()), String.class);
        if (valor.isEmpty()) {
            System.out.println("es nulo");
        } else {
            System.out.println("el valor es " + valor.get(0));
        }*/
        return model;
    }

    @RequestMapping(value = "/home.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getHome() {
        Users permisosUsuario = securityService.getCurrentUser();
        if (permisosUsuario != null) {
            String rolesActuales = permisosUsuario.getAuthorities().toString();

        }
        return new ModelAndView(new RedirectView("entre.htm"));

    }

    @RequestMapping(value = "/loginfailed.htm")
    public ModelAndView loginfailed(Principal principal) {
        ModelAndView model = new ModelAndView("loginfailed");
        model.addObject("error", "Usuario y contraseña inválidos");
        return model;
    }

    @RequestMapping(value = "/entre.htm")
    public ModelAndView entre(Principal principal,HttpServletRequest request) {
        ModelAndView model = new ModelAndView("logueado");
        HttpSession session = request.getSession();
        session.setAttribute("idusuario", securityService.getCurrentUser().getCedula());
        model.addObject("error", "me loguee");
        return model;
    }
    @RequestMapping(value = "/accesodenegado.htm")
     public ModelAndView accesDenied() {
         return new ModelAndView("accesodenegado");
     }
    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    /*
     @Override
     protected ModelAndView onSubmit(
     HttpServletRequest request, 
     HttpServletResponse response, 
     Object command, 
     BindException errors) throws Exception {
     ModelAndView mv = new ModelAndView(getSuccessView());
     //Do something...
     return mv;
     }
     */

    @RequestMapping(value = "/logout.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView(new RedirectView("index.htm"));
    }
    
}
