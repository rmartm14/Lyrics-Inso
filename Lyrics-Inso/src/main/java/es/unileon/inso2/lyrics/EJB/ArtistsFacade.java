/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Artists;

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
public class ArtistsFacade extends AbstractFacade<Artists> implements ArtistsFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistsFacade() {
        super(Artists.class);
    }
    @Override
    public Artists getArtist(String name) {
       String consulta="FROM Artists u WHERE u.name=:param1";
        Artists art = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", name);
            List<Artists> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                art = resultado.get(0);

            }
        }catch(Exception e){
            System.err.println("Artista no encontrado"+ e);
        }

        return art;
    }
}
