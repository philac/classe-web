package ca.classe.classe_service.entite;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.DaoBase;

public interface DaoSubject extends DaoBase<Subject, Integer> {

	Subject loadByIdWithCompetencies(Integer id);

}
