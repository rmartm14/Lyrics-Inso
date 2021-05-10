/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
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
    private Songs song;
    
    @PostConstruct
    public void ini(){
        song = new Songs();
    }
    
    public void registrar(){
        try {
            songEJB.create(song);
        } catch (Exception e) {
        }
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
