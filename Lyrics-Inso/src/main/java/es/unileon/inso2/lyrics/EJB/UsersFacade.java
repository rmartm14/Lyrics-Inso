/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.EJB;

import es.unileon.inso2.lyrics.modelo.Users;
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
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "lyricsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users verificarUsuario(Users user) {
        String consulta="FROM Users u WHERE u.name=:param1 and u.password=:param2";
        System.out.println(user.getName()+" "+ user.getPassword());
        Users us = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", user.getName());
            query.setParameter("param2", user.getPassword());
            List<Users> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                us = resultado.get(0);
                
            }
        }catch(Exception e){
            System.err.println("Usuario o contra no válidas"+ e);
        }
 
        return us;
    }


    public Users getUser(String name) {
       String consulta="FROM Users u WHERE u.name=:param1";
        Users us = null;
        try{
            Query query=em.createQuery(consulta);
            query.setParameter("param1", name);
            List<Users> resultado = query.getResultList();
            if(resultado.isEmpty() == false){
                us = resultado.get(0);
                
            }
        }catch(Exception e){
            System.err.println("Usuario no encontrado"+ e);
        }
 
        return us;
    }
    
    
}
