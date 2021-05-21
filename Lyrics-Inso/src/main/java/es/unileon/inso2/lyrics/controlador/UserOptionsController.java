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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author rafam
 */
@Named
@SessionScoped
public class UserOptionsController implements Serializable{
    @EJB
    private UsersFacadeLocal userEJB;
    private Users user;
    
    @PostConstruct
    public void init() {
        user = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    public String showEditar() {
        user = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return "/privado/normal/usuario/editUser.lyrics?faces-redirect=true";
    }
    
    public void editar(){
        this.userEJB.edit(user);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar canción", "Usuario editado con exito");
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

    public UsersFacadeLocal getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UsersFacadeLocal userEJB) {
        this.userEJB = userEJB;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
