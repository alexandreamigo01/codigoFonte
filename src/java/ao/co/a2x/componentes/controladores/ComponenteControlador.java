/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ao.co.a2x.componentes.controladores;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author a2x
 */
@Named
@ViewScoped
public class ComponenteControlador implements Serializable {
    
    private String i;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }
    
    
    
    
    
}
