package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Styles;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-29T00:09:40")
@StaticMetamodel(Group.class)
public class Group_ { 

    public static volatile SingularAttribute<Group, Integer> group_id;
    public static volatile SingularAttribute<Group, String> name;
    public static volatile ListAttribute<Group, Styles> styles;

}