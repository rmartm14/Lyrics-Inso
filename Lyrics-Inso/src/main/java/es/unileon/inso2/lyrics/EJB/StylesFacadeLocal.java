/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Styles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface StylesFacadeLocal {

    void create(Styles styles);

    void edit(Styles styles);

    void remove(Styles styles);

    Styles find(Object id);

    List<Styles> findAll();

    List<Styles> findRange(int[] range);

    int count();
    
}
