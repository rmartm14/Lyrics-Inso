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

    private List<List<Instruments>> listaInstrumentos;

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

    public void registrar() {
        try {
            //Creacion del grupo
            Group comprobar = groupEJB.getGroup(group.getName());
            if (comprobar == null) {

                List<Styles> grestilos = new ArrayList<Styles>();
                for (Styles staux : estilos) {

                    for (Styles streal : allStyles) {
                        if (staux.getName().equals(streal.getName())) {
                            grestilos.add(streal);
                            break;
                        }
                    }
                }

                this.group.setStyles(grestilos);
                groupEJB.create(group);

                //Rellenar la lista de group en cada estilo
                for (Styles est : grestilos) {
                    est.getGroups().add(group);
                    styleEJB.edit(est);

                }
                
                //Creacion de los artistas despues de haber creado el grupo
                for (Artists ar : artistas) {
                    List<Instruments> instrart = new ArrayList<Instruments>();
                    Artists comprobar2 = artistEJB.getArtist(ar.getName());
                    if (comprobar2 == null) {
                        for (Instruments instaux : ar.getInstruments()) {

                            for (Instruments instreal : allInstruments) {
                                if (instaux.getName().equals(instreal.getName())) {

                                    instrart.add(instreal);
                                    break;
                                }
                            }

                        }
                        ar.setInstruments(instrart);
                        ar.setGroup(group);
                        artistEJB.create(ar);
                        
                        //Rellenar la lista de artistas en cada instrumento
                        for (Instruments inst : instrart) {
                            inst.getArtists().add(ar);
                            instrumentEJB.edit(inst);
                        }

                    } else {
                        throw new Exception("El nombre de uno de los Artistas ya existe");
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
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registrar Grupo", "Campos incorrectos. Asegurese de que todos los campos están rellenos o cambie el nombre del grupo.");
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
    }

    public void addInstrumentIntoList(Artists artista) {

        Instruments instrumento = new Instruments();
        artista.getInstruments().add(instrumento);
    }

    public void dropArtistOutList(Artists artist) {

        this.artistas.remove(this.artistas.indexOf(artist));

    }

    public void dropStyleOutList(Styles estilo) {

        this.estilos.remove(this.estilos.indexOf(estilo));

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
    //Getters y Setters

    public List<Instruments> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instruments> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public List<List<Instruments>> getListaInstrumentos() {
        return listaInstrumentos;
    }

    public void setListaInstrumentos(List<List<Instruments>> listaInstrumentos) {
        this.listaInstrumentos = listaInstrumentos;
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
