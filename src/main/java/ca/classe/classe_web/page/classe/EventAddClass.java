package ca.classe.classe_web.page.classe;

import ca.classe.classe_modele.Classe;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EventAddClass implements Evenement<EventAddClass.Observer> {

	public interface Observer {
		void onAddClass(Object source, Classe bean);
	}

	public static final TypeEvenement<EventAddClass.Observer> TYPE = new TypeEvenement<EventAddClass.Observer>();
	private Classe bean;
	private Object source;
	
	public EventAddClass(Object source, Classe bean) {
		this.source = source;
		this.bean = bean;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onAddClass(source, bean);
	}

}
