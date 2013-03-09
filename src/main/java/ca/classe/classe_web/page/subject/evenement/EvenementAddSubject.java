package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_modele.Subject;
import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

import com.vaadin.data.util.BeanItem;

public class EvenementAddSubject implements Evenement<EvenementAddSubject.Observer> {

	public static interface Observer {
		void onAjouter(BeanItem<Subject> beanItem);
	}

	private BeanItem<Subject> beanItem;
    public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
	
	public EvenementAddSubject(BeanItem<Subject> beanItem) {
		this.beanItem = beanItem;
	}

	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observateur) {
		observateur.onAjouter(beanItem);
	}

}
