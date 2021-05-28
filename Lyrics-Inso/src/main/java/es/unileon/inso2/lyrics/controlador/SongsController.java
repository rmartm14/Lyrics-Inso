/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.ForosFacadeLocal;
import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.StylesFacadeLocal;
import es.unileon.inso2.lyrics.EJB.UsersFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Foros;
import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Songs;
import es.unileon.inso2.lyrics.modelo.Styles;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author alwop
 */
@Named
@RequestScoped
public class SongsController implements Serializable {

    @EJB
    private SongsFacadeLocal songEJB;
    @EJB
    private GroupFacadeLocal groupEJB;
    @EJB
    private StylesFacadeLocal styleEJB;
    @EJB
    private UsersFacadeLocal usersEJB;
    @EJB
    private ForosFacadeLocal forosEJB;

    private Songs song;
    private Group group;
    private Styles auxStyle;

    private List<Songs> allSongs;

    private List<Styles> allStyles;
    private String selectedStyle;
    private List<String> nameStyles;

    private List<Group> allGroups;
    private String selectedGroup;
    private List<String> nameGroups;
    
    private List<Songs> orderedSong;
    private boolean original;

    
    
    @PostConstruct
    public void ini() {
        song = new Songs();
        group = new Group();
        auxStyle = new Styles();

        selectedStyle = "";
        selectedGroup = "";

        allStyles = this.styleEJB.findAll();
        allGroups = this.groupEJB.findAll();
        allSongs = this.songEJB.findAll();
        
        nameStyles = new ArrayList<String>();
        nameGroups = new ArrayList<String>();
        
        original = true;

        this.initNameGroups();
        this.initNameStyles();
        
    }
    
    public List<Songs> getSongByUser() {
        return this.songEJB.getSongsByUser((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
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

    public void registrar() {

        try {
            //Comprobar nombre no existe
            Songs comprob = songEJB.getSong(song.getName());
            if(comprob == null){
                this.song.setGroup(this.getGroupByName(selectedGroup));
                this.song.setStyle(this.getStyleByName(selectedStyle));
                this.song.setOriginal(original);
                //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
                this.song.setUser((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
                songEJB.create(song);
                Foros foro = new Foros();
                foro.setSong(song);
                forosEJB.create(foro);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar canción", "Canción registrada con éxito");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
                String xhtml = "/Lyrics-Inso/privado/normal/paginaInitial.lyrics?faces-redirect=true";

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(xhtml);
                    //return xhtml;
                } catch (IOException ex) {
                    Logger.getLogger(SongsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                throw new Exception("Nombre de canción ya existe.");
            }
            
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrar canción", "Campos incorrectos. Asegurese de que todos los campos están rellenos o cambie el nombre de la canción.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        
    }

    public String editarCancion() {
        try {
            songEJB.edit(song);
        } catch (Exception e) {
            System.out.println("El error ha sido en editar cancion:");
            System.out.println(e.getMessage());
        }
        return "public/principal.lyrics?faces-redirect=true";
    }
    
    public void removeSong(String position) {
        try{
            System.out.println("Posicion:" + position);
            int pos = Integer.parseInt(position);
            String name = this.getSongByUser().get(pos).getName();
            Songs delSong = this.songEJB.getSong(name);
            songEJB.remove(delSong);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Canción eliminada", "Canción eliminada con éxito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }catch (Exception e){
            System.out.println("Error al borrar cancion.");
            System.out.println(e.getMessage());
        }
    }

    public void reloadStyles() {
        try{
            this.styleEJB.create(auxStyle);
            this.nameStyles.add(auxStyle.getName());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar estilo", "Estilo registrado con éxito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrar estilo", "Campos incorrectos. El nombre de estilo ya éxiste.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<Songs> getOrderedSong() {
        return orderedSong;
    }

    public void setOrderedSong(List<Songs> orderedSong) {
        this.orderedSong = orderedSong;
    }

    public UsersFacadeLocal getUsersEJB() {
        return usersEJB;
    }

    public void setUsersEJB(UsersFacadeLocal usersEJB) {
        this.usersEJB = usersEJB;
    }

    public List<Songs> getAllSongs() {
        return allSongs;
    }

    public void setAllSongs(List<Songs> allSongs) {
        this.allSongs = allSongs;
    }

    public Styles getAuxStyle() {
        return auxStyle;
    }

    public void setAuxStyle(Styles auxStyle) {
        this.auxStyle = auxStyle;
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

    public List<String> getNameStyles() {
        return nameStyles;
    }

    public void setNameStyles(List<String> nameStyles) {
        this.nameStyles = nameStyles;
    }

    public String getSelectedStyle() {
        return selectedStyle;
    }

    public void setSelectedStyle(String selectedStyle) {
        this.selectedStyle = selectedStyle;
    }

    public GroupFacadeLocal getGroupEJB() {
        return groupEJB;
    }

    public void setGroupEJB(GroupFacadeLocal groupEJB) {
        this.groupEJB = groupEJB;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    //Getters y Setters
    public void setAllStyles(List<Styles> allStyles) {
        this.allStyles = allStyles;
    }

    public SongsFacadeLocal getSongEJB() {
        return songEJB;
    }

    public void setSongEJB(SongsFacadeLocal songEJB) {
        this.songEJB = songEJB;
    }

    public Songs getSong() {
        return song;
    }

    public void setSong(Songs song) {
        this.song = song;
    }

    public ForosFacadeLocal getForosEJB() {
        return forosEJB;
    }

    public void setForosEJB(ForosFacadeLocal forosEJB) {
        this.forosEJB = forosEJB;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }
    
}