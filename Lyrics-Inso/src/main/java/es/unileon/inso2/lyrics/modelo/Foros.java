/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alwop
 */
@Entity
@Table (name="foros")
class Foros {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int foro_id;
    
    @JoinColumn(name="songs")
    @OneToOne
    private Songs song;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.foro_id;
        hash = 11 * hash + Objects.hashCode(this.song);
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
        final Foros other = (Foros) obj;
        if (this.foro_id != other.foro_id) {
            return false;
        }
        if (!Objects.equals(this.song, other.song)) {
            return false;
        }
        return true;
    }

    
    public int getForo_id() {
        return foro_id;
    }

    public Songs getSong() {
        return song;
    }

    public void setForo_id(int foro_id) {
        this.foro_id = foro_id;
    }

    public void setSong(Songs song) {
        this.song = song;
    }
    
}
