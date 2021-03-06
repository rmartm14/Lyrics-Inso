/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.ArtistsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.GroupFacadeLocal;
import es.unileon.inso2.lyrics.EJB.InstrumentsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.StylesFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Artists;
import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Instruments;
import es.unileon.inso2.lyrics.modelo.Styles;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@ViewScoped
public class GroupController implements Serializable {

    @EJB
    private GroupFacadeLocal groupEJB;
    private Group group;

    @EJB
    private InstrumentsFacadeLocal instrumentEJB;
    @EJB
    private StylesFacadeLocal styleEJB;
    @EJB
    private ArtistsFacadeLocal artistEJB;

    private List<Artists> artistas;
    private List<Styles> estilos;
    private List<Instruments> instrumentos;

    private ArtistisController controladorArt = new ArtistisController();

    private List<Styles> allStyles;

    private List<String> nameStyles;

    private List<Instruments> allInstruments;

    private List<String> nameInstrument;

    @PostConstruct
    public void ini() {
        group = new Group();
        artistas = new ArrayList<Artists>();
        estilos = new ArrayList<Styles>();
        this.addArtistIntoList();
        this.addStyleIntoList();

        this.allInstruments = this.instrumentEJB.findAll();

        nameInstrument = new ArrayList<String>();
        this.initNameInstrument();

        allStyles = this.styleEJB.findAll();
        nameStyles = new ArrayList<String>();
        this.initNameStyles();

    }

    private void initNameInstrument() {
        for (Instruments i : this.allInstruments) {
            nameInstrument.add(i.getName());
        }
    }

    private void initNameStyles() {
        for (Styles s : this.allStyles) {
            nameStyles.add(s.getName());
        }
    }

    public List<Group> getAllGroups() {
        return this.groupEJB.findAll();
    }

    public void registrar() {
        try {
            //Creacion del grupo
            Group comprobar = groupEJB.getGroup(group.getName());
            if (comprobar == null) {

                List<Styles> grestilos = new ArrayList<Styles>();
                for (Styles staux : estilos) {

                    for (Styles streal : allStyles) {
                        if (staux.getName().equals(streal.getName())) {
                            if (!grestilos.contains(streal)) {//si no tiene el estilo
                                grestilos.add(streal);
                            }
                        }
                    }
                }

                //this.group.setStyles(grestilos);
                groupEJB.create(group);

                group = groupEJB.getGroup(group.getName());

                group.setStyles(grestilos);
                groupEJB.edit(group);

                //Creacion de los artistas despues de haber creado el grupo
                for (Artists ar : artistas) {
                    List<Instruments> instrart = new ArrayList<Instruments>();
                    Artists comprobar2 = artistEJB.getArtist(ar.getName());
                    if (comprobar2 == null) {
                        for (Instruments instaux : ar.getInstruments()) {

                            for (Instruments instreal : allInstruments) {
                                if (instaux.getName().equals(instreal.getName())) {

                                    instrart.add(instreal);

                                }
                            }

                        }
                        ar.setGroup(group);

                        ar.setInstruments(new ArrayList<Instruments>());
                        artistEJB.create(ar);

                        ar = artistEJB.getArtist(ar.getName());
                        ar.setInstruments(instrart);
                        artistEJB.edit(ar);

                    }
                }

                String xhtml = "/Lyrics-Inso/privado/normal/paginaInitial.lyrics?faces-redirect=true";

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(xhtml);
                    //return xhtml;
                } catch (IOException ex) {
                    Logger.getLogger(SongsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                throw new Exception("El nombre del Grupo ya existe");

            }
        } catch (Exception e) {
            System.out.println(e.toString());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrar Grupo", "Campos incorrectos. Asegurese de que todos los campos est??n rellenos o no estan creados dentro de la aplicaci??n." + e.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void addArtistIntoList() {
        Artists artista = new Artists();
        //artista.setName("Introduzca un nombre de artista");
        List<Instruments> inst = new ArrayList<Instruments>();

        artista.setInstruments(inst);
        this.artistas.add(artista);
        this.addInstrumentIntoList(artista);

    }

    public void addStyleIntoList() {
        Styles estilo = new Styles();
        this.estilos.add(estilo);
        //Remover estilo de las opciones
    }

    public void addInstrumentIntoList(Artists artista) {

        Instruments instrumento = new Instruments();
        artista.getInstruments().add(instrumento);
    }

    public void dropArtistOutList(Artists artist) {

        this.artistas.remove(this.artistas.indexOf(artist));

    }

    public void dropStyleOutList() {
        try {
            this.estilos.remove(estilos.size() - 1);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrar Grupo", "No exiten estilos para eliminar");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void dropInstrumentOutList(Artists artist, Instruments instrumento) {
        artistas.get(this.artistas.indexOf(artist)).getInstruments().remove(instrumento);
    }

    public Styles getStyleByName(String name) {
        for (Styles s : this.allStyles) {
            if (s.getName().equals(name)) {
                return s;
            }

        }
        return null;
    }

    public void removeGroup(Group group) {
        try {
            group.setStyles(new ArrayList<Styles>());
            groupEJB.edit(group);
            groupEJB.remove(group);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar Grupo", "Grupo eliminado con exito.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            System.out.println("Error al borrar grupo.");
            System.out.println(e.getMessage());
        }
        String xhtml = "/Lyrics-Inso/privado/normal/paginaInitial.lyrics?faces-redirect=true";

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(xhtml);
            //return xhtml;
        } catch (IOException ex) {
            Logger.getLogger(SongsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Getters y Setters

    public List<Instruments> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instruments> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public List<Styles> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<Styles> estilos) {
        this.estilos = estilos;
    }

    public InstrumentsFacadeLocal getInstrumentEJB() {
        return instrumentEJB;
    }

    public void setInstrumentEJB(InstrumentsFacadeLocal instrumentEJB) {
        this.instrumentEJB = instrumentEJB;
    }

    public StylesFacadeLocal getStyleEJB() {
        return styleEJB;
    }

    public void setStyleEJB(StylesFacadeLocal styleEJB) {
        this.styleEJB = styleEJB;
    }

    public List<Styles> getAllStyles() {
        return allStyles;
    }

    public void setAllStyles(List<Styles> allStyles) {
        this.allStyles = allStyles;
    }

    public List<String> getNameStyles() {
        return nameStyles;
    }

    public void setNameStyles(List<String> nameStyles) {
        this.nameStyles = nameStyles;
    }

    public List<Instruments> getAllInstruments() {
        return allInstruments;
    }

    public void setAllInstruments(List<Instruments> allInstruments) {
        this.allInstruments = allInstruments;
    }

    public List<String> getNameInstrument() {
        return nameInstrument;
    }

    public void setNameInstrument(List<String> nameInstrument) {
        this.nameInstrument = nameInstrument;
    }

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
