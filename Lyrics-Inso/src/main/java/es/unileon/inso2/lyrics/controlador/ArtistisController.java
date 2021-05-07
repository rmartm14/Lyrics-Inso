/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.ArtistsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Artists;
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
public class ArtistisController implements Serializable{
    @EJB
    private ArtistsFacadeLocal artistEJB;
    private Artists artist;
    
    @PostConstruct
    public void ini(){
        artist = new Artists();
    }
    public void registrar(){
        try {
            artistEJB.create(artist);
        } catch (Exception e) {
        }    
    }
    //Getters y Setters
    public ArtistsFacadeLocal getArtistEJB() {
        return artistEJB;
    }

    public void setArtistEJB(ArtistsFacadeLocal artistEJB) {
        this.artistEJB = artistEJB;
    }

    public Artists getArtist() {
        return artist;
    }

    public void setArtist(Artists artist) {
        this.artist = artist;
    }
    
    
    
}
