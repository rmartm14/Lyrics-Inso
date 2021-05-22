/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Group;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alwop
 */
@Local
public interface GroupFacadeLocal {

    void create(Group group);

    void edit(Group group);

    void remove(Group group);

    Group find(Object id);

    List<Group> findAll();

    List<Group> findRange(int[] range);

    int count();
    
    public Group getGroup(String name);
    
}
