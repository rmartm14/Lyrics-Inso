/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Instruments;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface InstrumentsFacadeLocal {

    void create(Instruments instruments);

    void edit(Instruments instruments);

    void remove(Instruments instruments);

    Instruments find(Object id);

    List<Instruments> findAll();

    List<Instruments> findRange(int[] range);

    int count();
    
}
