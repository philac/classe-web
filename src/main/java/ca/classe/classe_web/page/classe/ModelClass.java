package ca.classe.classe_web.page.classe;

import java.util.List;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.ContexteApplicationUtils;
import ca.classe.classe_service.entite.ServiceClass;
import ca.classe.classe_service.entite.ServiceSubject;
import ca.classe.classe_web.mvp.ModelBaseImpl;

public class ModelClass extends ModelBaseImpl<ServiceClass> {

	private static ModelClass modelClass;
	
	private ServiceSubject serviceSubject;
	
	private ModelClass() {
		serviceSubject = ContexteApplicationUtils.getService(ServiceSubject.class);
	}
	
	public static ModelClass getInstance() {
		if (modelClass == null) {
			modelClass = new ModelClass();
		}
		return modelClass;
	}
	
	public Subject loadByIdWithClasses(Integer id) {
		return serviceSubject.loadByIdWithClasses(id);
	}

	public List<Subject> loadAllSubjects() {
		return serviceSubject.loadAll();
	}
	
	public void addClass(Classe classe) {
		service.add(classe);
	}

}
