/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alwop
 */

@Entity 
@Table (name="songs")
public class Songs implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int song_id;
    
    @JoinColumn(name="user_id")
    @ManyToOne
    private Users user_id;
    
    @JoinColumn(name="group_id")
    @ManyToOne
    private Group group_id;
    
    @JoinColumn(name="style")
    @ManyToOne
    private Styles style;
    
    @Column (name="original")
    private boolean original;
    @Column (name="visit_counter")
    private int visit_counter;
    @Column (name="name")
    private String name;
    @Column (name="lyrics")
    private String lyrics;
    @Column (name="grade")
    private float grade;  

    @JoinTable(
        name = "songinstr",
        joinColumns = @JoinColumn(name = "song_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="instrument_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Instruments> instrumentos;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.song_id;
        hash = 79 * hash + Objects.hashCode(this.user_id);
        hash = 79 * hash + Objects.hashCode(this.group_id);
        hash = 79 * hash + Objects.hashCode(this.style);
        hash = 79 * hash + (this.original ? 1 : 0);
        hash = 79 * hash + this.visit_counter;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.lyrics);
        hash = 79 * hash + Float.floatToIntBits(this.grade);
        hash = 79 * hash + Objects.hashCode(this.instrumentos);
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
        final Songs other = (Songs) obj;
        if (this.song_id != other.song_id) {
            return false;
        }
        if (this.original != other.original) {
            return false;
        }
        if (this.visit_counter != other.visit_counter) {
            return false;
        }
        if (Float.floatToIntBits(this.grade) != Float.floatToIntBits(other.grade)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lyrics, other.lyrics)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        if (!Objects.equals(this.group_id, other.group_id)) {
            return false;
        }
        if (!Objects.equals(this.style, other.style)) {
            return false;
        }
        if (!Objects.equals(this.instrumentos, other.instrumentos)) {
            return false;
        }
        return true;
    }

    
    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public void setUser(Users user) {
        this.user_id = user;
    }

    public void setGroup(Group group) {
        this.group_id = group;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public void setVisit_counter(int visit_counter) {
        this.visit_counter = visit_counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setInstrumentos(List<Instruments> instrumentos) {
        this.instrumentos = instrumentos;
    }

    
    public int getSong_id() {
        return song_id;
    }

    public Users getUser() {
        return user_id;
    }

    public Group getGroup() {
        return group_id;
    }

    public Styles getStyle() {
        return style;
    }

    public boolean isOriginal() {
        return original;
    }

    public int getVisit_counter() {
        return visit_counter;
    }

    public String getName() {
        return name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public float getGrade() {
        return grade;
    }

    public List<Instruments> getInstrumentos() {
        return instrumentos;
    }
    
    
}
