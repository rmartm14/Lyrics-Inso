/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.InstrumentsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Instruments;
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
public class InstrumentsController implements Serializable{
    @EJB
    private InstrumentsFacadeLocal intrumentEJB;
    private Instruments instrument;
    
    @PostConstruct
    public void ini(){
        instrument = new Instruments();
    }
    
    public void registrar(){
        try {
            intrumentEJB.create(instrument);
        } catch (Exception e) {
        }
    }
    
    //Getters y Setters

    public InstrumentsFacadeLocal getIntrumentEJB() {
        return intrumentEJB;
    }

    public void setIntrumentEJB(InstrumentsFacadeLocal intrumentEJB) {
        this.intrumentEJB = intrumentEJB;
    }

    public Instruments getInstrument() {
        return instrument;
    }

    public void setInstrument(Instruments instrument) {
        this.instrument = instrument;
    }
    
}
