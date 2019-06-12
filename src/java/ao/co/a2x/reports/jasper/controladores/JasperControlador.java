/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.reports.jasper.controladores;

import ao.co.a2x.model.Pessoa;
import ao.co.a2x.reports.jasper.JasperBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author a2x
 */
@Named
@ViewScoped
public class JasperControlador implements Serializable {
    
    JasperBean jasperBean = new JasperBean();

    public void abrir() {
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/jasper/template.jasper");
        String nomeDoFicheiroFinal = "novoFicheiro";
        Map parametersMap = new HashMap<>();
        parametersMap.put("imagemFundo", "Luis é um amor");

        List<Pessoa> lista = new ArrayList<>();
        Pessoa p = new Pessoa();
        p.setNome("Luis");
        p.setIdade(28);
        lista.add(p);

        try {
            jasperBean.abrirPDFJasper(reportPath, nomeDoFicheiroFinal, parametersMap, lista);
        } catch (NullPointerException ex) {
            Logger.getLogger(JasperBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JasperBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JasperBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirDocx(){
        
        String ficheiroFinal = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/jasper/doc.docx");
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/jasper/template.jasper");
        String nomeDoFicheiroFinal = "novoFicheiro";
        Map parametersMap = new HashMap<>();
        parametersMap.put("imagemFundo", "Luis é um amor");

        List<Pessoa> lista = new ArrayList<>();
        Pessoa p = new Pessoa();
        p.setNome("Luis");
        p.setIdade(28);
        lista.add(p);

        
         try {
            jasperBean.abrirDOCXJasper(reportPath,ficheiroFinal, nomeDoFicheiroFinal, parametersMap, lista);
        } catch (NullPointerException ex) {
            Logger.getLogger(JasperBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JasperBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
