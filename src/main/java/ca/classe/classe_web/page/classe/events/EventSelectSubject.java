package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventSelectSubject implements
		Evenement<EventSelectSubject.Observer> {

	public static interface Observer {
		void onSelect(Subject subject);
	}

	private Subject subject;
	public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
	
	public EventSelectSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onSelect(subject);
	}

}
