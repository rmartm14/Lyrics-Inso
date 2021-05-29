/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Songs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rafam
 */
@Named
@ViewScoped
public class FilteringController implements Serializable {
    
    @EJB
    private SongsFacadeLocal songsEJB;
    
    private String palabraFiltrado;
    private float valorFiltrado;
    private int[] valores;
    private List<Songs> lista;
    
    @PostConstruct
    public void init() {
        palabraFiltrado = "";
        valorFiltrado = 0.0f;
        valores = new int[10];
        generateNewValues();
        lista = this.songsEJB.findAll();
    }

    private void generateNewValues() {
        
        for(int i = 0; i < 10; i++){
            this.valores[i] = i*5;
        }  
    }
    
    

    public SongsFacadeLocal getSongsEJB() {
        return songsEJB;
    }

    public void setSongsEJB(SongsFacadeLocal songsEJB) {
        this.songsEJB = songsEJB;
    }

    public String getPalabraFiltrado() {
        return palabraFiltrado;
    }

    public void setPalabraFiltrado(String palabraFiltrado) {
        this.palabraFiltrado = palabraFiltrado;
    }

    public float getValorFiltrado() {
        return valorFiltrado;
    }

    public void setValorFiltrado(float valorFiltrado) {
        this.valorFiltrado = valorFiltrado;
    }

    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public List<Songs> getLista() {
        return lista;
    }

    public void setLista(List<Songs> lista) {
        this.lista = lista;
    }
    
    
    
}
