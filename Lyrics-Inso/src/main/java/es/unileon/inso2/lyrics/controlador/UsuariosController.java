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
import javax.faces.context.FacesContext;
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
    public void init(){
        user = new Users();
        System.out.println("Usuario inicializado");
    }
    public String registrar(){
        usersEJB.create(user);
        return "publico/principal.lyrics?faces-redirect=true";
    }
    
    public String validar(){
        String xhtml="";
        try{
            user = usersEJB.verificarUsuario(user);
        }
        catch(Exception  e){
            System.err.println("ERROR validando al usuario " + e);
        
        }
        //System.out.println(usuarios.getIdUsuario());
        if(user == null) {
            xhtml = "permisosInsuficientes.xhtml?faces-redirect=true";
            //System.out.println("Usuario denegado");
        }
        else{
            xhtml = "publico/principal.lyrics?faces-redirect=true";
            //System.out.println("Usuario correcto");
        }
        //Almacenar de forma global el usuario
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
        return xhtml;
    }
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //System.out.println("Saliendo");
        return "/login.xhtml?faces-redirect=true";
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
