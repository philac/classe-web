package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementDeleteSubject implements Evenement<EvenementDeleteSubject.Observer> {

	public interface Observer {
		void onDelete(Subject subject);
	}

	private Subject subject;
    public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
    
    public EvenementDeleteSubject(Subject subject) {
    	this.subject = subject;
    }
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onDelete(subject);
	}

	
}
