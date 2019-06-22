/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.model;

import ao.co.a2x.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author a2x
 */
public class Email implements Serializable {

    private String to;
    private String from;
    private String cc;
    private String bcc;
    private String subject;
    private String text;
    private List<String> files;
    private String contentType;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    
    public void setText(String message) {
        if (contentType != null && !contentType.isEmpty()) {
            this.text = Utilitarios.acentuarCaractersHtml(message);
        } else {
            this.text = message;
        }
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
