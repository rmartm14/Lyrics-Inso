/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;


import es.unileon.inso2.lyrics.EJB.UsersFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
    
    private List<Users> orderedList;
    
    @PostConstruct
    public void init(){
        user = new Users();
        this.orderUserByGrade();
    }
    public String registrar(){
        Users user2 = usersEJB.getUser(user.getName());
        if(user2 != null){//Usuario ya existe
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya existe.", "Por favor introduzca otro nombre de usuario"));
            user = user2;
            return "";
        }
        else{//no existe el usuario
            usersEJB.create(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            return "publico/principal.lyrics?faces-redirect=true";
        }
    }
    
    public String validar(){
        String xhtml="";
        Users copiaUser = user;
        try{
            user = usersEJB.verificarUsuario(user);
        }
        catch(Exception  e){
            System.err.println("ERROR validando al usuario " + e);
        
        }
        //System.out.println(usuarios.getIdUsuario());
        if(user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la entrada de datos.", "El nombre de usuario y/o la contraseña son incorrectos"));
            //System.out.println("Saliendo");
            user = copiaUser;
        return "";
            //System.out.println("Usuario denegado");
        }
        else{
            xhtml = "privado/normal/paginaInitial.lyrics?faces-redirect=true";
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

    public String getCurrentUserName() {
        Users current = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return current.getName();
    }
    
    public void orderUserByGrade() {
        List<Users> allUsers = this.usersEJB.findAll();
        Collections.sort(allUsers, new Comparator<Users>() {
            @Override
            public int compare(Users o1, Users o2) {
                return -Float.compare(o1.getGrade(), o2.getGrade());
            }
          });
        System.out.println(allUsers.toString());
        this.orderedList = allUsers;

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

    public List<Users> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<Users> orderedList) {
        this.orderedList = orderedList;
    }
    
}
