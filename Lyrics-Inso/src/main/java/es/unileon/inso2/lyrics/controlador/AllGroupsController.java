/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Group;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@ViewScoped
public class AllGroupsController implements Serializable{
    @EJB
    private GroupFacadeLocal groupEJB;
    
    private List<Group> allGroup;
    
    @PostConstruct
    public void ini() {
        allGroup = cargarGrupos();
    }

    private List<Group> cargarGrupos() {
        System.out.println("Cargo los grupos");
        List<Group> grupos = new ArrayList<Group>();
        grupos = groupEJB.findAll();
        return grupos;
    }
    
    public String verEditarGrupo(Group group){
        System.out.println("Entro en ver EDITAR");
        String xhtml = "editGrupo.lyrics?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupoEditar", group);
        return xhtml;
    }
    public String verMostrarGrupo(Group group){
        System.out.println("Entro en ver mostrarGrupo");
        String xhtml = "verGrupo.lyrics?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("grupoEditar", group);
        return xhtml;
    }
    public GroupFacadeLocal getGroupEJB() {
        return groupEJB;
    }

    public void setGroupEJB(GroupFacadeLocal groupEJB) {
        this.groupEJB = groupEJB;
    }

    public List<Group> getAllGroup() {
        return allGroup;
    }

    public void setAllGroup(List<Group> allGroup) {
        this.allGroup = allGroup;
    }
           
}
