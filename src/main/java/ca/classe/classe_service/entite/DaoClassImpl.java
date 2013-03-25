package ca.classe.classe_service.entite;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Classe_;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_modele.Subject_;
import ca.classe.classe_service.DaoBaseImpl;

@Repository
public class DaoClassImpl extends DaoBaseImpl<Classe, Integer>
implements DaoClass {
	
	
	@Override
	public Classe loadWithSubjectAndCompetencies(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Classe> cq = cb.createQuery(Classe.class);
		Root<Classe> root = cq.from(Classe.class);
		
		Fetch<Classe, Subject> subjectFetch = root.fetch(Classe_.subject, JoinType.LEFT);
		subjectFetch.fetch(Subject_.competencies, JoinType.LEFT);
		Predicate predicateId = cb.equal(root.get(Classe_.id), id);
		cq.where(predicateId);
		
		return entityManager.createQuery(cq).getSingleResult();
	}
}
