/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Songs;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@ViewScoped
public class SongsController implements Serializable{
    @EJB
    private SongsFacadeLocal songEJB;
    @EJB
    private GroupFacadeLocal groupEJB;
    private Songs song;
    private Group group;
    
    @PostConstruct
    public void ini(){
        song = new Songs();
    }
    
    public void registrar(){

        try {
            this.group = groupEJB.find(group.getName());
            this.song.setGroup(group);
            songEJB.create(song);
        } catch (Exception e) {
        }
    }
    
    public String editarCancion() {
        try{
            songEJB.edit(song);
        }
        catch(Exception e){
            System.out.println("El error ha sido en editar cancion:");
            System.out.println(e.getMessage());
        }
        return "public/principal.lyrics?faces-redirect=true";
    }
    
    //Getters y Setters

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
    
}
