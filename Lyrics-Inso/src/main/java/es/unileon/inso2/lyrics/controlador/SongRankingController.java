/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Songs;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rafam
 */
@Named
@ViewScoped
public class SongRankingController implements Serializable {
    
    @EJB
    private SongsFacadeLocal songEJB;
    
    private List<Songs> orderedList;
    
    @PostConstruct
    public void ini() { 
        this.orderSongByGrade();
    }
    public void orderSongByGrade() {
        List<Songs> allSongs = this.songEJB.findAll();
        Collections.sort(allSongs, new Comparator<Songs>() {
            @Override
            public int compare(Songs o1, Songs o2) {
                return -Float.compare(o1.getGrade(), o2.getGrade());
            }
        });
        this.orderedList = allSongs;
     }

    public SongsFacadeLocal getSongEJB() {
        return songEJB;
    }

    public void setSongEJB(SongsFacadeLocal songEJB) {
        this.songEJB = songEJB;
    }

    public List<Songs> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<Songs> orderedList) {
        this.orderedList = orderedList;
    }
    
}
