/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Artists;
import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Instruments;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@ViewScoped
public class GroupController implements Serializable{
    @EJB
    private GroupFacadeLocal groupEJB;
    private Group group;
    
    private List<Artists> artistas;
    
    @PostConstruct
    public void ini(){
        group = new Group();
        artistas = new ArrayList<Artists>();
        this.addArtistIntoList();
        this.addArtistIntoList();
    }
    
    public void registrar(){
        try {
            groupEJB.create(group);
        } catch (Exception e) {
        }
    }
    public void addArtistIntoList(){
        System.out.println("Metiendo un nuevo artist");
        Artists artista = new Artists();
        artista.setName("Introduzca un nombre de artista");
        artistas.add(artista);
    }
    //Getters y Setters

    public GroupFacadeLocal getGroupEJB() {
        return groupEJB;
    }

    public void setGroupEJB(GroupFacadeLocal groupEJB) {
        this.groupEJB = groupEJB;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public List<Artists> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artists> artistas) {
        this.artistas = artistas;
    }
}
