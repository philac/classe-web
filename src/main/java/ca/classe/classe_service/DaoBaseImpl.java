package ca.classe.classe_service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.classe.classe_modele.BaseEntite;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DaoBaseImpl <E extends BaseEntite<PK>, PK extends Serializable> implements DaoBase<E, PK> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<E> classeEntite;
	
	protected Class<PK> classePK;
	
	protected Set<Class<?>> primitives;
	
	protected Metamodel metaModele;
	
    @SuppressWarnings("unchecked")
    protected DaoBaseImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        classeEntite = (Class<E>) type.getActualTypeArguments()[0];
        classePK = (Class<PK>) type.getActualTypeArguments()[1];
    }

    @Override
    public List<E> loadAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(classeEntite);
        criteriaQuery.distinct(true);
        criteriaQuery.from(classeEntite);

        TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<E> chargerToutAvecAssociations() {
	       return chargerToutAvecAssociations(getAssociations().toArray(new Attribute[0]));
	}

	@Override
	public List<E> chargerToutAvecAssociations(
			Attribute<? super E, ?>... associations) {
        CriteriaQuery<E> cq = entityManager.getCriteriaBuilder().createQuery(classeEntite);
        cq.distinct(true);
        Root<E> root = cq.from(classeEntite);

        for (Attribute<? super E, ?> attribut : associations) {
            root.fetch(attribut.getName(), JoinType.LEFT);
        }

        TypedQuery<E> requete = entityManager.createQuery(cq);
        return requete.getResultList();
	}

	@Override
	public E chargerParId(PK id) {
		return entityManager.find(classeEntite, id);
	}

	@Override
	public E chargerParIdAvecAssociations(PK id) {
		return chargerParIdAvecAssociations(id, getAssociations().toArray(new Attribute[0]));
	}

	@Override
	public E chargerParIdAvecAssociations(PK id,
			Attribute<? super E, ?>... associations) {
	     CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<E> cq = cb.createQuery(classeEntite);
	        Root<E> root = cq.from(classeEntite);
	        for (Attribute<? super E, ?> association : associations) {
	            root.fetch(association.getName(), JoinType.LEFT);
	        }

	        Predicate conditionIdentifiant = cb.equal(root.get(root.getModel().getId(classePK)), id);

	        cq.select(root);
	        cq.where(conditionIdentifiant);

	        TypedQuery<E> requete = entityManager.createQuery(cq);

	        return requete.getSingleResult();
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void ajouter(E entite) {
		entityManager.persist(entite);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public E modifier(E entite) {
		return entityManager.merge(entite);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.MANDATORY)
    public void supprimer(E entite) {
        E entiteASupprimer = entityManager.getReference(classeEntite, entite.getId());
        entityManager.remove(entiteASupprimer);
	}

	@Override
	public Long compter() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<E> root = cq.from(classeEntite);
        cq.select(cb.count(root));

        return entityManager.createQuery(cq).getSingleResult();
	}
	
	   /**
     * Déterminer si un attribut est une association.
     * 
     * @return true si la classe de l'attribut est une association.
     */
    protected boolean isAssociation(Class<?> classe) {
        if (classe == null) {
            return false;
        }

        if (classe.isEnum()) {
            return true;
        }

        if (primitives == null) {
            initialiserPrimitives();
        }

        return !primitives.contains(classe);
    }

    private void initialiserPrimitives() {
        primitives = new HashSet<Class<?>>();
        primitives.add(Character.class);
        primitives.add(String.class);
        primitives.add(Boolean.class);
        primitives.add(Byte.class);
        primitives.add(Short.class);
        primitives.add(Integer.class);
        primitives.add(Long.class);
        primitives.add(BigDecimal.class);
        primitives.add(Date.class);
    }

    /**
     * Obtenir les association de premier niveau.
     * 
     * @return la liste de toutes les associations.
     */
    protected List<Attribute<? super E, ?>> getAssociations() {
        initialiseMetaModel();

        List<Attribute<? super E, ?>> associations = new ArrayList<Attribute<? super E, ?>>();
        EntityType<E> typeEntite = metaModele.entity(classeEntite);
        for (Attribute<? super E, ?> attribut : typeEntite.getAttributes()) {
            if (isAssociation(attribut.getJavaType())) {
                associations.add(attribut);
            }
        }

        return associations;
    }

    private void initialiseMetaModel() {
        if (metaModele == null) {
            metaModele = entityManager.getMetamodel();
        }
    }

}
