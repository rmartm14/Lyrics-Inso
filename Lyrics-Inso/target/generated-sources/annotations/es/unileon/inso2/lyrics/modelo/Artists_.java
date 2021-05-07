package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Group;
import es.unileon.inso2.lyrics.modelo.Instruments;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-06T18:59:32")
@StaticMetamodel(Artists.class)
public class Artists_ { 

    public static volatile ListAttribute<Artists, Instruments> instruments;
    public static volatile SingularAttribute<Artists, String> name;
    public static volatile SingularAttribute<Artists, Integer> artist_id;
    public static volatile SingularAttribute<Artists, Group> group;

}