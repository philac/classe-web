package ca.classe.classe_service.entite;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.ServiceBaseImpl;

@Service
public class ServiceCompetencyImpl extends ServiceBaseImpl implements
		ServiceCompetency {
	
	private DaoCompetency daoCompetency;
	
	@Inject
	public ServiceCompetencyImpl(DaoCompetency daoCompetency) {
		this.daoCompetency = daoCompetency;
	}
	
	@Override
	public void Modify(Competency competency) {
		daoCompetency.modifier(competency);
	}

}
