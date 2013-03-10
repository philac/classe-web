package ca.classe.classe_service.entite;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.ServiceBaseImpl;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServiceCompetencyImpl extends ServiceBaseImpl implements
		ServiceCompetency {
	
	private DaoCompetency daoCompetency;
	
	@Inject
	public ServiceCompetencyImpl(DaoCompetency daoCompetency) {
		this.daoCompetency = daoCompetency;
	}
	
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void Modify(Competency competency) {
		daoCompetency.modifier(competency);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Competency competency) {
		daoCompetency.supprimer(competency);
	}

}
