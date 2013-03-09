package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_modele.Competency;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementModifyCompetency implements
		Evenement<EvenementModifyCompetency.Observer> {

	public interface Observer {
		void onModifyCompetency(Competency competency);
	}

	public static final TypeEvenement<Observer> TYPE = new TypeEvenement<EvenementModifyCompetency.Observer>();

	private Competency competency;

	public EvenementModifyCompetency(Competency competency) {
		this.competency = competency;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onModifyCompetency(competency);
	}

}
