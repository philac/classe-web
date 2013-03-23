package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventRequestAddClass implements
		Evenement<EventRequestAddClass.Observer> {

	public interface Observer {
		void onRequestAddClass();
	}

	public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onRequestAddClass();
	}

}
