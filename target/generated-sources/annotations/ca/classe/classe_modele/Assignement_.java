package ca.classe.classe_modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Assignement.class)
public abstract class Assignement_ {

	public static volatile SingularAttribute<Assignement, Integer> id;
	public static volatile SingularAttribute<Assignement, Float> total;
	public static volatile SingularAttribute<Assignement, String> title;
	public static volatile SingularAttribute<Assignement, Float> weight;
	public static volatile SingularAttribute<Assignement, AssignmentType> assignmentType;

}

