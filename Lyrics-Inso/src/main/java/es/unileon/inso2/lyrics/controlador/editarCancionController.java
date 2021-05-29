/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.StylesFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Songs;
import es.unileon.inso2.lyrics.modelo.Styles;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Sam
 */
@Named
@ViewScoped
public class editarCancionController implements Serializable{
    @EJB
    private SongsFacadeLocal songEJB;
    
    private Songs cancion;
    private String nombreCanOriginal;
    private Users user;
    
    @EJB
    private GroupFacadeLocal groupEJB;
    private List<Group> allGroups;
    private String selectedGroup;
    private List<String> nameGroups;
    
     @EJB
    private StylesFacadeLocal styleEJB;
    private List<Styles> allStyles;
    private String selectedStyle;
    private List<String> nameStyles;
    
    @PostConstruct
    public void init(){
        allGroups = this.groupEJB.findAll();
        nameGroups = new ArrayList<String>();
        this.initNameGroups();
        
        allStyles = this.styleEJB.findAll();
        nameStyles = new ArrayList<String>();
        this.initNameStyles();
        
        System.out.println("Editando ");
        cancion =(Songs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cancionEditar");
        this.selectedGroup = cancion.getGroup().getName();
        this.selectedStyle = cancion.getStyle().getName();
        this.nombreCanOriginal = cancion.getName();
        
    }
    public Styles getStyleByName(String name) {
        for (Styles s : this.allStyles) {
            if (s.getName().equals(name)) {
                return s;
            }

        }
        return null;
    }

    public Group getGroupByName(String name) {
        for (Group s : this.allGroups) {
            if (s.getName().equals(name)) {
                return s;
            }

        }
        return null;
    }
    public String actualizar(){
        try {
            //Comprobar nombre no existe
            Songs comprob = songEJB.getSong(cancion.getName());
            if(comprob == null){
                
                System.out.println(selectedGroup);
                this.cancion.setGroup(this.getGroupByName(selectedGroup));
                this.cancion.setStyle(this.getStyleByName(selectedStyle));
                songEJB.edit(cancion);
            }
            else if(comprob.getName() == this.nombreCanOriginal ){
                System.out.println(selectedGroup);
                this.cancion.setGroup(this.getGroupByName(selectedGroup));
                this.cancion.setStyle(this.getStyleByName(selectedStyle));
                songEJB.edit(cancion);
            }
            else{
                throw new Exception("Nombre de canción ya existe.");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualizar canción", "Campos incorrectos. Asegurese de que todos los campos están rellenos o cambie el nombre de la canción.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }
        String xhtml = "misCanciones.lyrics?faces-redirect=true";
        return xhtml;
    }
    public void initNameStyles() {
        for (Styles s : this.allStyles) {
            nameStyles.add(s.getName());
        }
    }
    public void initNameGroups() {
        for (Group s : this.allGroups) {
            nameGroups.add(s.getName());
        }
    }

    public List<Group> getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(List<Group> allGroups) {
        this.allGroups = allGroups;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public List<String> getNameGroups() {
        return nameGroups;
    }

    public void setNameGroups(List<String> nameGroups) {
        this.nameGroups = nameGroups;
    }
    
    public Songs getCancion() {
        return cancion;
    }

    public void setCancion(Songs cancion) {
        this.cancion = cancion;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public GroupFacadeLocal getGroupEJB() {
        return groupEJB;
    }

    public void setGroupEJB(GroupFacadeLocal groupEJB) {
        this.groupEJB = groupEJB;
    }

    public StylesFacadeLocal getStyleEJB() {
        return styleEJB;
    }

    public void setStyleEJB(StylesFacadeLocal styleEJB) {
        this.styleEJB = styleEJB;
    }

    public List<Styles> getAllStyles() {
        return allStyles;
    }

    public void setAllStyles(List<Styles> allStyles) {
        this.allStyles = allStyles;
    }

    public String getSelectedStyle() {
        return selectedStyle;
    }

    public void setSelectedStyle(String selectedStyle) {
        this.selectedStyle = selectedStyle;
    }

    public List<String> getNameStyles() {
        return nameStyles;
    }

    public void setNameStyles(List<String> nameStyles) {
        this.nameStyles = nameStyles;
    }
    
}
