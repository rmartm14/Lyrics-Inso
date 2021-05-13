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
 * @author rafam
 */
@Entity
@Table(name="instruments")
public class Instruments implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int instrument_id;
    @Column (name="name")
    private String name;
    @Column (name="price")
    private float price;
    
    @JoinTable(
        name = "instruartist",
        joinColumns = @JoinColumn(name = "instruments", nullable = false),
        inverseJoinColumns = @JoinColumn(name="artists", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artists> artists;          

    @ManyToMany(mappedBy = "instrumentos")
    private List<Songs> songs;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.instrument_id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Float.floatToIntBits(this.price);
        hash = 29 * hash + Objects.hashCode(this.artists);
        hash = 29 * hash + Objects.hashCode(this.songs);
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
        final Instruments other = (Instruments) obj;
        if (this.instrument_id != other.instrument_id) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.artists, other.artists)) {
            return false;
        }
        if (!Objects.equals(this.songs, other.songs)) {
            return false;
        }
        return true;
    }

    public void setInstrument_id(int instrument_id) {
        this.instrument_id = instrument_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }

    public int getInstrument_id() {
        return instrument_id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public List<Songs> getSongs() {
        return songs;
    }
    
    
    
    
}
