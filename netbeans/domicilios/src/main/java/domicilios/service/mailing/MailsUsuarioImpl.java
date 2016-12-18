/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service.mailing;

import domicilios.dao.ValidacionUsuarioDao;
import domicilios.entidad.Usuario;
import domicilios.entidad.ValidacionUsuarios;
import domicilios.util.LeerXml;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 *
 * @author edeani
 */
@Service
public class MailsUsuarioImpl implements MailsUsuario {

    @Autowired
    private MailingService mailingService;
    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    @Autowired
    private ValidacionUsuarioDao validacionUsuarioDao;

    @Autowired
    private LeerXml leerXml;

    @Value("${mail.administracion}")
    private String fromEmail;
    
    @Override
    public void mailRegistro(Usuario usuario) {
        StringBuilder content = new StringBuilder();
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("idusuario", usuario.getIdusuario());
            ValidacionUsuarios vu = validacionUsuarioDao.queryOpjectJpa(leerXml.getQuery("ValidacionUsuarioJpa.findXidusuario"), parametros);
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("token", vu.getToken());
            modelo.put("mail", usuario.getCorreo());
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getConfiguration().getTemplate("confirmacion_registro.html"), modelo));
            mailingService.sendMail(fromEmail, usuario.getCorreo(), "Bienvenido a Colmbian Broaster. Activa tu cuenta", content.toString());
        } catch (IOException | TemplateException e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
    }

    
}
