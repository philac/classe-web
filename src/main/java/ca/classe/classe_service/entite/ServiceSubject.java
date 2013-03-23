package ca.classe.classe_service.entite;

import java.util.List;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.ServiceBase;

public interface ServiceSubject extends ServiceBase {

	List<Subject> loadAll();

	void add(Subject bean);

	void delete(Subject bean);

	void modify(Subject subject);

	Subject loadById(Integer subjectId);

	Subject loadByIdWithCompetencies(Integer id);
	
	Subject loadByIdWithClasses(Integer id);
}
