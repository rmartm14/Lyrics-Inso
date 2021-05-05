/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @JoinColumn(name="style")
    @ManyToOne
    private Styles style;

    public int getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(int instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.instrument_id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Float.floatToIntBits(this.price);
        hash = 79 * hash + Objects.hashCode(this.style);
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
        if (!Objects.equals(this.style, other.style)) {
            return false;
        }
        return true;
    }
    
    
            

}
