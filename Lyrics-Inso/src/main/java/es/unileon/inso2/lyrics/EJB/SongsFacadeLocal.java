/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Songs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface SongsFacadeLocal {

    void create(Songs songs);

    void edit(Songs songs);

    void remove(Songs songs);

    Songs find(Object id);

    List<Songs> findAll();

    List<Songs> findRange(int[] range);

    int count();
    
}
