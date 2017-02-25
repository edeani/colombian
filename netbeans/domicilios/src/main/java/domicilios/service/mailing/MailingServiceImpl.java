/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service.mailing;

import java.util.concurrent.Future;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
@Transactional
public class MailingServiceImpl implements MailingService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public Future<Boolean> sendMail(String from, String to, String subject, final String contenido) {
        MimeMessagePreparator preparator;
        preparator = (MimeMessage mimeMessage) -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setTo(to);
            /**
             * Hay un segundo parámetro booleano para indicar si se adjunta
             * archivo o no
             * use the true flag to indicate you need a
             * multipart message helper.setText(text, true);
             *
             * Additionally, let's add a resource as an attachment as
             * well.
             * helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
             */
            helper.setText(contenido,true);
        };
        
        
        try {
            mailSender.send(preparator);
            System.out.println("Message has been sent.............................");
            return new AsyncResult<>(Boolean.TRUE);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
           return new AsyncResult<>(Boolean.FALSE);
        }
    }

}
