package ca.classe.classe_web.page.subject;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PageWithPresenter;

public class PageSubjects extends PageWithPresenter {

	private static final long serialVersionUID = -6557873439700914937L;

	public PageSubjects(BusEvenement busEvenement) {
		addComponent(presenterFactory.createPresenter(PresenterSubjects.class).getComponent());
	}	
}
