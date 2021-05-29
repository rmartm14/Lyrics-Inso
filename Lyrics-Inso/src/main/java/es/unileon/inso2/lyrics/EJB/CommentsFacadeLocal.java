/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Comments;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rafam
 */
@Local
public interface CommentsFacadeLocal {

    void create(Comments comments);

    void edit(Comments comments);

    void remove(Comments comments);

    Comments find(Object id);

    List<Comments> findAll();

    List<Comments> findRange(int[] range);

    int count();
    
}
