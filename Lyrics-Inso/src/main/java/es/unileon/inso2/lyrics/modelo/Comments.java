/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.io.Serializable;
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
 * @author alwop
 */

@Entity
@Table (name="comments")
public class Comments implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int comment_id;
    
    @JoinColumn(name="user_id")
    @ManyToOne
    private Users user;
    
    @JoinColumn(name="foro_id")
    @ManyToOne
    private Foros foro;
    
    @Column (name="commentContent")
    private String comment;
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.comment_id;
        hash = 37 * hash + Objects.hashCode(this.user);
        hash = 37 * hash + Objects.hashCode(this.foro);
        hash = 37 * hash + Objects.hashCode(this.comment);
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
        final Comments other = (Comments) obj;
        if (this.comment_id != other.comment_id) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.foro, other.foro)) {
            return false;
        }
        return true;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setForo(Foros foro) {
        this.foro = foro;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_id() {
        return comment_id;
    }

    public Users getUser() {
        return user;
    }

    public Foros getForo() {
        return foro;
    }

    public String getComment() {
        return comment;
    }
    
    
    
}
