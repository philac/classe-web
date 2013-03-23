package ca.classe.classe_service.entite;

import org.springframework.stereotype.Repository;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.DaoBaseImpl;

@Repository
public class DaoClassImpl extends DaoBaseImpl<Classe, Integer>
implements DaoClass {

}
