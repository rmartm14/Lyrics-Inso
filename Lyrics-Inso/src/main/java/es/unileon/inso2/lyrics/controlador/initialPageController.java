/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Songs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author rafam
 */
@Named
@SessionScoped
public class initialPageController implements Serializable {
    @EJB
    private SongsFacadeLocal songEJB;
    
    private String auxtxt;
    private Songs auxSong;
    
    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cancion_buscada", new Songs());
        auxtxt = "";
    }
    
    public String mostrarCancion(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("cancion_buscada", this.songEJB.getSong(auxtxt));
        auxSong = (Songs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cancion_buscada");
        auxtxt = "";
        return "/privado/normal/cancion/mostrarCancion.lyrics?faces-redirect=true";
    }
    
    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> countryList = new ArrayList<>();
        List<Songs> songs = this.songEJB.findAll();
        for (Songs country : songs) {
            countryList.add(country.getName());
        }
        List<String> resultList = new ArrayList<>();
        for(String s: countryList){
            if(s.contains(query)){
                resultList.add(s);
            }
        }
        return resultList;
    }

    public SongsFacadeLocal getSongEJB() {
        return songEJB;
    }

    public void setSongEJB(SongsFacadeLocal songEJB) {
        this.songEJB = songEJB;
    }

    public String getAuxtxt() {
        return auxtxt;
    }

    public void setAuxtxt(String auxtxt) {
        this.auxtxt = auxtxt;
    }

    public Songs getAuxSong() {
        return auxSong;
    }

    public void setAuxSong(Songs auxSong) {
        this.auxSong = auxSong;
    }
}
