/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Foros;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface ForosFacadeLocal {

    void create(Foros foros);

    void edit(Foros foros);

    void remove(Foros foros);

    Foros find(Object id);

    List<Foros> findAll();

    List<Foros> findRange(int[] range);

    int count();
    
}
