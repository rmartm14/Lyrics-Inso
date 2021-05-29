/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Songs;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class misCancionesController implements Serializable{
    @EJB
    private SongsFacadeLocal songEJB;

    private List<Songs> allSongs;
    
    @PostConstruct
    public void ini() {
        

        allSongs = cargarCanciones();


    }
    public List<Songs> cargarCanciones(){
        
        List<Songs> canciones = new ArrayList<Songs>();
        Users user =(Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        canciones = songEJB.getAllSongs(user);
        return canciones;
    }
    public String verEditar(Songs song){
        String xhtml = "editCancion.lyrics?faces-redirect=true";
        //Almacenar de forma global el usuario
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cancionEditar", song);
        return xhtml;
        
    }
public void removeSong(String position) {
        try{
            System.out.println("Posicion:" + position);
            int pos = Integer.parseInt(position);
            String name = this.allSongs.get(pos).getName();
            Songs delSong = this.songEJB.getSong(name);
            songEJB.remove(delSong);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Canción eliminada", "Canción eliminada con éxito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Users user =(Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            allSongs = songEJB.getAllSongs(user);
        }catch (Exception e){
            System.out.println("Error al borrar cancion.");
            System.out.println(e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error borrando", "Error eliminando canción.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public SongsFacadeLocal getSongEJB() {
        return songEJB;
    }

    public void setSongEJB(SongsFacadeLocal songEJB) {
        this.songEJB = songEJB;
    }

    public List<Songs> getAllSongs() {
        return allSongs;
    }

    public void setAllSongs(List<Songs> allSongs) {
        this.allSongs = allSongs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.songEJB);
        hash = 79 * hash + Objects.hashCode(this.allSongs);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final misCancionesController other = (misCancionesController) obj;
        if (!Objects.equals(this.songEJB, other.songEJB)) {
            return false;
        }
        if (!Objects.equals(this.allSongs, other.allSongs)) {
            return false;
        }
        return true;
    }
    
}
