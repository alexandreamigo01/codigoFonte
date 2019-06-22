/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.model;

import java.io.Serializable;

/**
 *
 * @author a2x
 * Classe qie define 
 * 
 */
public class ConfiguracaoApp implements Serializable{

    //Nome da aplicação
    private String nomeApp;
    
    // Directorio onde serão armazenados os arquivos da app
    private String home;
    
    // Configuração de Email
    
    // Email usado pelo app para enviar emails
    private String emailDoSistema ;
    
    private String senhaDoEmail; 
    
    private String preferIPv4Stack;

    private String protocol;

    private String starttls;

    private String host;

    private String auth;

    private String port;

    public String getNomeApp() {
        return nomeApp;
    }

    public void setNomeApp(String nomeApp) {
        this.nomeApp = nomeApp;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getEmailDoSistema() {
        return emailDoSistema;
    }

    public void setEmailDoSistema(String emailDoSistema) {
        this.emailDoSistema = emailDoSistema;
    }

    public String getSenhaDoEmail() {
        return senhaDoEmail;
    }

    public void setSenhaDoEmail(String senhaDoEmail) {
        this.senhaDoEmail = senhaDoEmail;
    }

    public String getPreferIPv4Stack() {
        return preferIPv4Stack;
    }

    public void setPreferIPv4Stack(String preferIPv4Stack) {
        this.preferIPv4Stack = preferIPv4Stack;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    
    
    
}
