/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Artists;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface ArtistsFacadeLocal {

    void create(Artists artists);

    void edit(Artists artists);

    void remove(Artists artists);

    Artists find(Object id);

    List<Artists> findAll();

    List<Artists> findRange(int[] range);

    int count();
    
    public Artists getArtist(String name);
    
}
