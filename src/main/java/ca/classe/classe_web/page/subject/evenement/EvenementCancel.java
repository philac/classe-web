package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementCancel implements Evenement<EvenementCancel.Observer> {

	public interface Observer {
		void onCancel();
	}

	public static final TypeEvenement<Observer> TYPE = new TypeEvenement<EvenementCancel.Observer>();
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onCancel();
	}

}
