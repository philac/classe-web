package ca.classe.classe_service.entite;

import org.springframework.stereotype.Repository;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.DaoBaseImpl;

@Repository
public class DaoCompetencyImp extends DaoBaseImpl<Competency, Integer>
		implements DaoCompetency {

}
