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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class EditarGrupoController implements Serializable{
    @EJB
    private GroupFacadeLocal groupEJB;
    @EJB
    private InstrumentsFacadeLocal instrumentEJB;
    @EJB
    private StylesFacadeLocal styleEJB;
    @EJB
    private ArtistsFacadeLocal artistEJB;
    
    private Group group;
    private String nameGroup;
    
    private List<Artists> artistas;
    private List<Styles> estilos;
    private List<Styles> estilosAntiguos;
    private List<Instruments> instrumentos;
    
    private List<Styles> allStyles;
    private List<String> nameStyles;
    
    private List<Instruments> allInstruments;
    private List<String> nameInstrument;
    
    @PostConstruct
    public void init(){
        
        allStyles = this.styleEJB.findAll();
        nameStyles = new ArrayList<String>();
        this.initNameStyles();
        
        this.allInstruments = this.instrumentEJB.findAll();
        nameInstrument = new ArrayList<String>();
        this.initNameInstrument();
        
        group =(Group) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grupoEditar");
        nameGroup = group.getName();
        estilos = group.getStyles();
        estilosAntiguos=group.getStyles();
        artistas = artistEJB.getArtistsByGroup(group);
        
        
    }
    
    public String actualizar(){
        try {
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
                groupEJB.edit(group);
                
            }else if(comprobar.getName() == this.nameGroup){
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
                groupEJB.edit(group);
                //elimianamos la relacion de los antiguos estilos con el grupo
                for (Styles estilo : estilosAntiguos) {
                    estilo.getGroups().remove(group);
                    styleEJB.edit(estilo);
                }
                //Rellenar la lista de group en cada estilo
                for (Styles est : grestilos) {
                    est.getGroups().add(group);
                    styleEJB.edit(est);

                }
            }else{
                throw new Exception("Nombre de grupo ya existe.");
            }
        } catch (Exception e) {
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualizar Grupo", "Campos incorrectos. Asegurese de que todos los campos están rellenos o cambie el nombre del grupo.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }
        
        String xhtml = "allGrupo.lyrics?faces-redirect=true";
        return xhtml;
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
    
    public void addArtistIntoList() {
        Artists artista = new Artists();
        //artista.setName("Introduzca un nombre de artista");
        List<Instruments> inst = new ArrayList<Instruments>();

        artista.setInstruments(inst);
        this.artistas.add(artista);
        this.addInstrumentIntoList(artista);

    }
    
    public void addInstrumentIntoList(Artists artista) {
        Instruments instrumento = new Instruments();
        artista.getInstruments().add(instrumento);
    }
    
    public void addStyleIntoList() {
        Styles estilo = new Styles();
        this.estilos.add(estilo);
    }
    
    public void dropArtistOutList(Artists artist) {

        this.artistas.remove(this.artistas.indexOf(artist));
        artistEJB.remove(artist);
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
    
    //Getter y Setter

    public GroupFacadeLocal getGroupEJB() {
        return groupEJB;
    }

    public void setGroupEJB(GroupFacadeLocal groupEJB) {
        this.groupEJB = groupEJB;
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

    public ArtistsFacadeLocal getArtistEJB() {
        return artistEJB;
    }

    public void setArtistEJB(ArtistsFacadeLocal artistEJB) {
        this.artistEJB = artistEJB;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public List<Artists> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artists> artistas) {
        this.artistas = artistas;
    }

    public List<Styles> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<Styles> estilos) {
        this.estilos = estilos;
    }

    public List<Instruments> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<Instruments> instrumentos) {
        this.instrumentos = instrumentos;
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
    
    
}
