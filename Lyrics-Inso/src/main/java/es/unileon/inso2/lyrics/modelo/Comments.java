/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

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
public class Comments {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int comment_id;
    
    @JoinColumn(name="users")
    @ManyToOne
    private Users user;
    
    @JoinColumn(name="foro")
    @ManyToOne
    private Foros foro;
    
    
}
