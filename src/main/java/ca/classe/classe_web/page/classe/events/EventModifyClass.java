package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventModifyClass implements
		Evenement<EventModifyClass.Observer> {

	public interface Observer {
		void onModifyClass(Classe classe);
	}
	
	public static TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
	private Classe classe;

	public EventModifyClass(Classe classe) {
		this.classe = classe;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onModifyClass(classe);
	}

}
