/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service.mailing;

import domicilios.entidad.Usuario;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void mailRegistro(Usuario usuario) {
        StringBuilder content = new StringBuilder();
        try {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("nombre", "A");
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getConfiguration().getTemplate("confirmacion_registro.html"), modelo));
            mailingService.sendMail("anloder4@gmail.com", "virguspower@yahoo.com", "Prueba", content.toString());
        } catch (IOException | TemplateException e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
    }

}
