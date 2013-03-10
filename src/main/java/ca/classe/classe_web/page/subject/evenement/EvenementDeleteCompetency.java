package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementDeleteCompetency implements Evenement<EvenementDeleteCompetency.Observer> {

	public interface Observer {
		void onDelete(Competency competency);
	}

	private Competency competency;
    public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
    
    public EvenementDeleteCompetency(Competency competency) {
    	this.competency = competency;
    }
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onDelete(competency);
	}

	
}

