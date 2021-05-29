/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Group;
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
public class GroupFacade extends AbstractFacade<Group> implements GroupFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupFacade() {
        super(Group.class);
    }

    @Override
    public Group getGroup(String name) {
        String consulta="FROM Group u WHERE u.name=:param1";
       
        Group group = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", name);
            List<Group> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                group = resultado.get(0);
                
            }
        }catch(Exception e){
            System.err.println("Usuario o contra no válidas"+ e);
        }
 
        return group;
    }
    
}
