package ca.classe.classe_web.page.classe;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PageWithPresenter;

import com.vaadin.ui.Label;

public class PageClasses extends PageWithPresenter {

	private static final long serialVersionUID = -6557873439700914937L;

	public PageClasses(BusEvenement busEvenement) {
		addComponent(new Label("page Classe")/*presenterFactory.createPresenter(PresenterSubjects.class).getComponent()*/);
	}	
}
