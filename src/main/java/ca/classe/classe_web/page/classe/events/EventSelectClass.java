package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventSelectClass implements Evenement<EventSelectClass.Observer> {

	public interface Observer {
		void onSelectClass(Classe classe);
	}
	
	public static final TypeEvenement<EventSelectClass.Observer> TYPE = new TypeEvenement<EventSelectClass.Observer>();
	private Classe classe;
	
	public EventSelectClass(Classe classe) {
		this.classe = classe;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onSelectClass(classe);
	}


}
