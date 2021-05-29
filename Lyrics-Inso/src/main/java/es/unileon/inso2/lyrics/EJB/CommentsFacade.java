/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Comments;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rafam
 */
@Stateless
public class CommentsFacade extends AbstractFacade<Comments> implements CommentsFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentsFacade() {
        super(Comments.class);
    }
    
}
