package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventRequestClassModification implements
		Evenement<EventRequestClassModification.Ovserver> {

	public interface Ovserver {
		void onRequestClassModification(Classe classe);
	}
	
	public static TypeEvenement<Ovserver> TYPE = new TypeEvenement<Ovserver>();
	private Classe classe;

	public EventRequestClassModification(Classe classe) {
		this.classe = classe;
	}

	@Override
	public TypeEvenement<Ovserver> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Ovserver observateur) {
		observateur.onRequestClassModification(classe);
	}

}
