/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.inso2.lyrics.controlador;

import es.unileon.inso2.lyrics.EJB.CommentsFacadeLocal;
import es.unileon.inso2.lyrics.modelo.Comments;
import java.io.Serializable;
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
public class CommentsController implements Serializable{
    @EJB
    private CommentsFacadeLocal commentsEJB;
    private Comments comment;
    
    @PostConstruct
    public void ini(){
        comment = new Comments();
    }
    
    public void registrar(){
        try {
            commentsEJB.create(comment);
        } catch (Exception e) {
        }
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
    
}
