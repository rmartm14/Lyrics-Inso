/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Songs;
import es.unileon.inso2.lyrics.modelo.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sam
 */
@Stateless
public class SongsFacade extends AbstractFacade<Songs> implements SongsFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SongsFacade() {
        super(Songs.class);
    }
    public Songs getSong(String name) {
       String consulta="FROM Songs u WHERE u.name=:param1";
        Songs song = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", name);
            List<Songs> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                song = resultado.get(0);

            }
        }catch(Exception e){
            System.err.println("Usuario no encontrado"+ e);
        }

        return song;
    }
    public List<Songs> getAllSongs(Users us){
        List<Songs> resultado = new ArrayList<Songs>();
        String consulta="FROM Songs u WHERE u.user_id=:param1";
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", us);
            resultado = query.getResultList();
            
        }catch(Exception e){
            System.err.println("Usuario o contra no válidas"+ e);
        }
 
        return resultado;
    }
}
