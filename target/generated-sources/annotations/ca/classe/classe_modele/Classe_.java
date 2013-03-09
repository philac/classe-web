package ca.classe.classe_modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Classe.class)
public abstract class Classe_ {

	public static volatile SingularAttribute<Classe, Integer> id;
	public static volatile SetAttribute<Classe, Student> students;
	public static volatile SingularAttribute<Classe, String> level;
	public static volatile SetAttribute<Classe, Subject> subjects;
	public static volatile SingularAttribute<Classe, String> name;

}

