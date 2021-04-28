/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.modelo;

import java.io.Serializable;
import java.util.Date;
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

}
