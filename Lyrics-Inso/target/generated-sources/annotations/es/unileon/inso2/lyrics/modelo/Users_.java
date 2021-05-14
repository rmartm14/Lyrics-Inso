package es.unileon.inso2.lyrics.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-14T21:30:35")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Boolean> role;
    public static volatile SingularAttribute<Users, Integer> user_id;
    public static volatile SingularAttribute<Users, Float> grade;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Date> fecha_nacimiento;

}