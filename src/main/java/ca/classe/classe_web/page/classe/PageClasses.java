package ca.classe.classe_web.page.classe;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PageWithPresenter;

public class PageClasses extends PageWithPresenter {

	private static final long serialVersionUID = -6557873439700914937L;

	public PageClasses(BusEvenement busEvenement) {
		addComponent(presenterFactory.createPresenter(PresenterClasses.class).getComponent());
	}	
}
