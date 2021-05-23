/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.CommentsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.ForosFacadeLocal;
import es.unileon.inso2.lyrics.EJB.SongsFacadeLocal;
import es.unileon.inso2.lyrics.EJB.UsersFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Comments;
import es.unileon.inso2.lyrics.modelo.Foros;
import es.unileon.inso2.lyrics.modelo.Songs;
import es.unileon.inso2.lyrics.modelo.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alwop
 */
@Named
@SessionScoped
public class CommentsController implements Serializable{
    @EJB
    private CommentsFacadeLocal commentsEJB;
    @EJB
    private SongsFacadeLocal songsEJB;
    @EJB
    private ForosFacadeLocal forosEJB;
    @EJB
    private UsersFacadeLocal userEJB;
    
    private Comments comment;
    private Foros foro;
    private Songs song;
   
    
    @PostConstruct
    public void ini(){
        comment = new Comments();
        song = (Songs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cancion_buscada");
       
    }
    
    public void actualizarSong(){
         song = (Songs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cancion_buscada");;
    }
    
    public Foros getSongForo(){
        this.actualizarSong();
            List<Foros> allforos = this.forosEJB.findAll();
            Foros foro = null;
            for(Foros f: allforos){
                if(f.getSong().getSong_id() == this.song.getSong_id()){
                    return f;
                }
            }  
            return foro;
    }
    public void setCommentForo(){
        this.actualizarSong();
            List<Foros> allforos = this.forosEJB.findAll();
            for(Foros f: allforos){
                if(f.getSong().getSong_id() == this.song.getSong_id()){
                    comment.setForo(f);
                    break;
                } 
            }
    }
    
    public List<Comments> getSongComments(){
        this.actualizarSong();
        List<Comments> allComments = this.commentsEJB.findAll(); 
        List<Comments> finalComments = new ArrayList<>();
        for(Comments c: allComments){
            if(c.getForo().getSong().getSong_id() == this.song.getSong_id()){
                finalComments.add(c);
            }
        }
        return finalComments;
    }
     
    public float getSongGrade() {
        this.actualizarSong();
        return song.getGrade();
    }
    
    public void comentar(){
        this.actualizarSong();
        try {
            this.setCommentForo();
            comment.setUser((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
            commentsEJB.create(comment);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Comentario", "Comentario inscrito con éxito");
            FacesContext.getCurrentInstance().addMessage(null, message);
            comment = new Comments(); 
        } catch (Exception e) {
        }
    }
    
    public void addGrade(int num){
        this.actualizarSong();
        Users user = this.song.getUser();
        float grades = findUserGrade(user);
        
        this.song.setGrade(this.song.getGrade()+num);
        this.songsEJB.edit(song);
        
        user.setGrade(grades);
        this.userEJB.edit(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Puntuación Registrada", "Puntuación Registrada con éxito");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private float findUserGrade(Users user) {
        this.actualizarSong();
        float grade = 0;
        for(Songs s: this.songsEJB.findAll()){
            if(s.getUser().getUser_id() == user.getUser_id()){
                grade = grade + s.getGrade();
            }
        }
        return grade;
    }
    
    
   
    //Getters y Setters

    public CommentsFacadeLocal getCommentsEJB() {
        return commentsEJB;
    }

    public void setCommentsEJB(CommentsFacadeLocal commentsEJB) {
        this.commentsEJB = commentsEJB;
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public SongsFacadeLocal getSongsEJB() {
        return songsEJB;
    }

    public void setSongsEJB(SongsFacadeLocal songsEJB) {
        this.songsEJB = songsEJB;
    }

    public ForosFacadeLocal getForosEJB() {
        return forosEJB;
    }

    public void setForosEJB(ForosFacadeLocal forosEJB) {
        this.forosEJB = forosEJB;
    }

    public Foros getForo() {
        return foro;
    }

    public void setForo(Foros foro) {
        this.foro = foro;
    }

    public Songs getSong() {
        return song;
    }

    public void setSong(Songs song) {
        this.song = song;
    }


}
