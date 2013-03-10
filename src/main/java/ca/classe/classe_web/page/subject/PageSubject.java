package ca.classe.classe_web.page.subject;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.mvp.PageWithPresenter;

public class PageSubject extends PageWithPresenter {


	public PageSubject(BusEvenement busEvenement, Integer subjectId, com.vaadin.server.Page parentPage) {
		PresenterSubject presenter = (PresenterSubject) presenterFactory.createPresenter(PresenterSubject.class);
		presenter.setSubject(subjectId, parentPage);
		addComponent(presenter.getComponent());
	}

	private static final long serialVersionUID = 5894665749599379405L;

	
}
