package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Group;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-03T21:15:46")
@StaticMetamodel(Styles.class)
public class Styles_ { 

    public static volatile SingularAttribute<Styles, String> characteristics;
    public static volatile SingularAttribute<Styles, String> name;
    public static volatile ListAttribute<Styles, Group> groups;
    public static volatile SingularAttribute<Styles, Integer> style_id;

}