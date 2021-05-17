/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author rafam
 */
@Entity
@Table(name="styles")
public class Styles implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int style_id;
    @Column (name="name")
    private String name;
    @Column (name="characteristics")
    private String characteristics;

     @JoinTable(
        name = "stylesgroups",
        joinColumns = @JoinColumn(name = "style_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="group_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.style_id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.characteristics);
        hash = 29 * hash + Objects.hashCode(this.groups);
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
        final Styles other = (Styles) obj;
        if (this.style_id != other.style_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.characteristics, other.characteristics)) {
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)) {
            return false;
        }
        return true;
    }

    public void setStyle_id(int style_id) {
        this.style_id = style_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getStyle_id() {
        return style_id;
    }

    public String getName() {
        return name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public List<Group> getGroups() {
        return groups;
    }
    
    
}
