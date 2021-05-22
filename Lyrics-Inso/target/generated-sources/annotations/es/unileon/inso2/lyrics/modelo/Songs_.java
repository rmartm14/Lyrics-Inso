package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Instruments;
import es.unileon.inso2.lyrics.modelo.Styles;
import es.unileon.inso2.lyrics.modelo.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-22T15:58:57")
@StaticMetamodel(Songs.class)
public class Songs_ { 

    public static volatile SingularAttribute<Songs, Integer> song_id;
    public static volatile SingularAttribute<Songs, Boolean> original;
    public static volatile SingularAttribute<Songs, Users> user_id;
    public static volatile SingularAttribute<Songs, Group> group_id;
    public static volatile SingularAttribute<Songs, Float> grade;
    public static volatile SingularAttribute<Songs, String> name;
    public static volatile SingularAttribute<Songs, Styles> style;
    public static volatile SingularAttribute<Songs, String> lyrics;
    public static volatile SingularAttribute<Songs, Integer> visit_counter;
    public static volatile ListAttribute<Songs, Instruments> instrumentos;

}