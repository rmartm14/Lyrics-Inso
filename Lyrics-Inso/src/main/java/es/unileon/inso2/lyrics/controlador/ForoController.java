/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.ForosFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Foros;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@ViewScoped
public class ForoController implements Serializable{
    @EJB
    private ForosFacadeLocal foroEJB;
    private Foros foro;
    
    @PostConstruct
    public void ini(){
        foro = new Foros();
    }
    
    //Getters y Setters

    public ForosFacadeLocal getForoEJB() {
        return foroEJB;
    }

    public void setForoEJB(ForosFacadeLocal foroEJB) {
        this.foroEJB = foroEJB;
    }

    public Foros getForo() {
        return foro;
    }

    public void setForo(Foros foro) {
        this.foro = foro;
    }
    
}
