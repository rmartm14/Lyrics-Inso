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
    
    private String oldVal, newVal, newValConfirm;
    
    @PostConstruct
    public void init() {
        user = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
    }
    
    public String showEditar() {
        user = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return "/privado/normal/usuario/editUser.lyrics?faces-redirect=true";
    }
    
        public void editar() {
        try {
            //Comprobar nombre no existe
            user.setName(user.getName().toLowerCase());
            Users comprob = userEJB.getUser(user.getName());

            //System.out.println(comprob.getName() +" "+ user.getName());
            if (comprob == null || comprob.getName().equalsIgnoreCase(user.getName())) {
                this.userEJB.edit(user);

            } else {
                throw new Exception("Nombre de usuario ya existe.");
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizando información de usuario", "Usuario actualizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualizar usuario", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }

    public void editarPass() {
        int flag = this.comprobarPass();

        //Completamente comprobado
        if (flag == 0) {
            this.user.setPassword(newVal);
            this.editar();
            oldVal = newVal = newValConfirm = "";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizando de contraseña", "Actualización exitosa.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        //Falla la pass actual-> flag 1
        if (flag == 1) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en contraseña", "La contraseña actual no coincide");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        //Falla la pass comprobada -> flag 2
        if (flag == 2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en contraseña", "Las nuevas contraseñas no coinciden.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public int comprobarPass(){
        boolean actual = this.oldVal.equals(this.user.getPassword());
        boolean confirm = this.newVal.equals(this.newValConfirm);
        int returnValue = 0;
        if(actual == true  && confirm == true){
            returnValue = 0;
        }
        else if(actual == false){
            returnValue = 1;
        }
        else{
            returnValue = 2;
        }
        
       return returnValue;
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

    public String getOldVal() {
        return oldVal;
    }

    public void setOldVal(String oldVal) {
        this.oldVal = oldVal;
    }

    public String getNewVal() {
        return newVal;
    }

    public void setNewVal(String newVal) {
        this.newVal = newVal;
    }

    public String getNewValConfirm() {
        return newValConfirm;
    }

    public void setNewValConfirm(String newValConfirm) {
        this.newValConfirm = newValConfirm;
    }
    
    
}
