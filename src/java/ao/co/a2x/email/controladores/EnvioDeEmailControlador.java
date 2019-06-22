/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.email.controladores;

import ao.co.a2x.email.EnvioDeEmail;
import ao.co.a2x.model.ConfiguracaoApp;
import ao.co.a2x.model.Email;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author a2x
 */
@Named
@ViewScoped
public class EnvioDeEmailControlador implements Serializable{
    
    private Email email;
    
    private ConfiguracaoApp config;
    
    private EnvioDeEmail enviador;
    
    public void enviarEmail(){
        try {
            email = new Email();
            config = new ConfiguracaoApp();
            enviador = new EnvioDeEmail();
            
            email.setTo("alexandreamigo01@gmail.com");
            email.setCc("alexandreamigo01@gmail.com");
            email.setBcc("alexandreamigo01@hotmail.com");
            email.setFrom("alexandreamigo01@hotmail.com");
            email.setSubject("Primeiro email");
            email.setText("Foi enviado o primeiro email");
            
            config.setEmailDoSistema("alexquima2x@gmail.com");
            config.setSenhaDoEmail("xxxxxxxx");
            config.setAuth("true");
            config.setHost("smtp.gmail.com");
            config.setPort("465"); // 465 587
            config.setPreferIPv4Stack("true");
            config.setProtocol("smtp");
            config.setStarttls("true");
            
            enviador.sendMail(email, config);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioDeEmailControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
