package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementModifySubject implements Evenement<EvenementModifySubject.Observer> {

	public interface Observer {
		void onModify(Subject subject);
	}

	private Subject subject;
	public final static TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
	
	public EvenementModifySubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onModify(subject);
	}

}
