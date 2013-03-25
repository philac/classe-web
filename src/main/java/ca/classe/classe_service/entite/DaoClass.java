package ca.classe.classe_service.entite;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.DaoBase;

public interface DaoClass extends DaoBase<Classe, Integer>{

	Classe loadWithSubjectAndCompetencies(Integer id);

}
