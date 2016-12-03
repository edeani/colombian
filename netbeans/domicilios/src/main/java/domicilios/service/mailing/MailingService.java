/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service.mailing;

/**
 *
 * @author edeani
 */
public interface MailingService {
    void sendMail(String from,String to,String subject,final String contenido);
}
