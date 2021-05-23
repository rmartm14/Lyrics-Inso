/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

/**
 *
 * @author alwop
 */
@Entity 
@Table (name="artists")
public class Artists {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int artist_id;
    
    @Column (name="name")
    private String name;
    
    @JoinColumn(name="group_id")
    @ManyToOne
    private Group group;
    
    @ManyToMany(mappedBy = "artists")
    private List<Instruments> instruments;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.artist_id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.group);
        hash = 29 * hash + Objects.hashCode(this.instruments);
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
        final Artists other = (Artists) obj;
        if (this.artist_id != other.artist_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.group, other.group)) {
            return false;
        }
        if (!Objects.equals(this.instruments, other.instruments)) {
            return false;
        }
        return true;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setInstruments(List<Instruments> instruments) {
        this.instruments = instruments;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public List<Instruments> getInstruments() {
        return instruments;
    }
        
}
