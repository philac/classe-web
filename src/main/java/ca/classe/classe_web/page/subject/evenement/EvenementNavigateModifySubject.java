package ca.classe.classe_web.page.subject.evenement;

import ca.classe.classe_service.commun.Evenement;
import ca.classe.classe_service.commun.TypeEvenement;

public class EvenementNavigateModifySubject implements Evenement<EvenementNavigateModifySubject.Observer> {
	
	public static interface Observer {
		void onNavigateToModify(Integer subjectId);
	}

    public static final TypeEvenement<Observer> TYPE = new TypeEvenement<Observer>();
    private Integer subjectId;
    
    public EvenementNavigateModifySubject(Integer integer) {
    	this.subjectId = integer;
    }
	
	@Override
	public TypeEvenement<Observer> getType() {
		return TYPE;
	}

	@Override
	public void notifierObservateur(Observer observer) {
		observer.onNavigateToModify(subjectId);
		
	}


}
