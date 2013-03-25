package ca.classe.classe_service.entite;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.ServiceBase;

public interface ServiceClass extends ServiceBase {

	void add(Classe classe);

	void modify(Classe classe);

	Classe loadClassWithSubjectAndCompetencies(Integer id);

}
