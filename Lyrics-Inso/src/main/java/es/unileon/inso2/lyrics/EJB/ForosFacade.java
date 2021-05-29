/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Foros;
import es.unileon.inso2.lyrics.modelo.Songs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alwop
 */
@Stateless
public class ForosFacade extends AbstractFacade<Foros> implements ForosFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ForosFacade() {
        super(Foros.class);
    }

    @Override
    public Foros findForoBySongId(int song_id) {
        String consulta="FROM Foros u WHERE u.song_id=:param1";
        Foros foro = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", song_id);
            List<Foros> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                foro = resultado.get(0);

            }
        }catch(Exception e){
            System.err.println("Usuario no encontrado"+ e);
        }

        return foro;
    }
    
}
