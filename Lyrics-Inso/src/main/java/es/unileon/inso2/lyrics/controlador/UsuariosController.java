/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;


import es.unileon.inso2.lyrics.EJB.UsersFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Sam
 */
@Named
@ViewScoped
public class UsuariosController implements Serializable{
    @EJB
    private UsersFacadeLocal usersEJB;
    private Users user;
    @PostConstruct
    public void ini(){
        user = new Users();
    }
    public void registrar(){
        usersEJB.create(user);
    }
    public UsersFacadeLocal getUsersEJB() {
        return usersEJB;
    }

    public void setUsersEJB(UsersFacadeLocal usersEJB) {
        this.usersEJB = usersEJB;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
