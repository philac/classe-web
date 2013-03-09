package ca.classe.classe_modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Competency.class)
public abstract class Competency_ {

	public static volatile SingularAttribute<Competency, Integer> id;
	public static volatile SingularAttribute<Competency, Float> weight;
	public static volatile SingularAttribute<Competency, Subject> subject;
	public static volatile SingularAttribute<Competency, String> description;
	public static volatile SingularAttribute<Competency, String> name;

}

