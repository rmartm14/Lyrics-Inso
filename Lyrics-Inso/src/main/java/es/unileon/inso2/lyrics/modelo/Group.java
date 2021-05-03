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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author alwop
 */
@Entity 
@Table (name="groups1")
class Group {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int group_id;
    
    @Column (name="name")
    private String name;

    @ManyToMany(mappedBy = "groups")
    private List<Styles> styles;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.group_id;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.styles);
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
        final Group other = (Group) obj;
        if (this.group_id != other.group_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.styles, other.styles)) {
            return false;
        }
        return true;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyles(List<Styles> styles) {
        this.styles = styles;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String getName() {
        return name;
    }

    public List<Styles> getStyles() {
        return styles;
    }
    
    
}
