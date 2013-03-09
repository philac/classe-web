package ca.classe.classe_service.entite;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.ServiceBaseImpl;

@Service
public class ServiceSubjectImpl extends ServiceBaseImpl implements
		ServiceSubject {

	private DaoSubject daoSubject;
	
	@Inject
	public ServiceSubjectImpl(DaoSubject daoSubject) {
		this.daoSubject = daoSubject;
	}
	
	@Override
	public List<Subject> loadAll() {
		return daoSubject.chargerToutAvecAssociations();
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void add(Subject bean) {
		daoSubject.ajouter(bean);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Subject bean) {
		daoSubject.supprimer(bean);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modify(Subject subject) {
		daoSubject.modifier(subject);
	}

	@Override
	public Subject loadById(Integer subjectId) {
		return daoSubject.chargerParId(subjectId);
	}
	
	@Override
	public Subject loadByIdWithCompetencies(Integer id) {
		return daoSubject.loadByIdWithCompetencies(id);
	}
}
