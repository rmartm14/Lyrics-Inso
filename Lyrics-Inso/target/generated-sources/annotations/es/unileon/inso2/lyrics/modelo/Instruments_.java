package es.unileon.inso2.lyrics.modelo;

import es.unileon.inso2.lyrics.modelo.Artists;
import es.unileon.inso2.lyrics.modelo.Songs;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-14T19:53:45")
@StaticMetamodel(Instruments.class)
public class Instruments_ { 

    public static volatile ListAttribute<Instruments, Artists> artists;
    public static volatile SingularAttribute<Instruments, Float> price;
    public static volatile ListAttribute<Instruments, Songs> songs;
    public static volatile SingularAttribute<Instruments, String> name;
    public static volatile SingularAttribute<Instruments, Integer> instrument_id;

}