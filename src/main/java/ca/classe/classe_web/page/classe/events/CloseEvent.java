package ca.classe.classe_web.page.classe.events;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class CloseEvent implements Evenement<CloseEvent.Observer> {

	public interface Observer {
		void onClose(Object source, Classe classe);
	}

	public static final TypeEvenement<Observer> TYPE = new TypeEvenement<CloseEvent.Observer>();
	private Object source;
	private Classe classe;

	public CloseEvent(Object source, Classe classe) {
		this.source = source;
		this.classe = classe;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onClose(source, classe);
		
	}

}
