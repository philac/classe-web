package ca.classe.classe_service.entite;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.DaoBaseImpl;

@Repository
public class DaoSubjectImpl extends DaoBaseImpl<Subject, Integer> implements
		DaoSubject {
	
	@Override
	public Subject loadByIdWithCompetencies(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
		Root<Subject> root = cq.from(Subject.class);
		
		root.fetch(Subject_.competencies);
		Predicate predicateId = cb.equal(root.get(Subject_.id), id);
		cq.where(predicateId);
		
		return entityManager.createQuery(cq).getSingleResult();
	}
}
