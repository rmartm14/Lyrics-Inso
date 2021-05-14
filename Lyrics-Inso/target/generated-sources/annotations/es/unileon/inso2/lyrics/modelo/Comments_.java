package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Foros;
import es.unileon.inso2.lyrics.modelo.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-14T19:53:45")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Foros> foro;
    public static volatile SingularAttribute<Comments, String> comment;
    public static volatile SingularAttribute<Comments, Integer> comment_id;
    public static volatile SingularAttribute<Comments, Users> user;

}