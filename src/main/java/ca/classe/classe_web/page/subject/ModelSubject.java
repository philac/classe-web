package ca.classe.classe_web.page.subject;

import java.util.List;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.ContexteApplicationUtils;
import ca.classe.classe_service.entite.ServiceCompetency;
import ca.classe.classe_service.entite.ServiceSubject;
import ca.classe.classe_web.mvp.ModelBaseImpl;

public class ModelSubject extends ModelBaseImpl<ServiceSubject>{
	
	private ServiceCompetency serviceCompetency;
	
	private static ModelSubject singleton = null;
	
	private ModelSubject() {
		serviceCompetency = ContexteApplicationUtils.getService(ServiceCompetency.class);
	}

	public List<Subject> loadSubjects() {
		return service.loadAll();
	}

	public void add(Subject bean) {
		service.add(bean);
	}
	
	public void delete(Subject bean) {
		service.delete(bean);
	}

	public void modify(Subject subject) {
		service.modify(subject);
	}

	public Subject loadSubject(Integer subjectId) {
		return service.loadByIdWithCompetencies(subjectId);
	}

	public void modify(Competency competency) {
		serviceCompetency.Modify(competency);
	}
	
	public static ModelSubject getInstance() {
		if (singleton == null) {
			singleton = new ModelSubject();
		}
		return singleton;
	}
}
