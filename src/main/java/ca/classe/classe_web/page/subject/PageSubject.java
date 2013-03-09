package ca.classe.classe_web.page.subject;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.page.Page;

import com.vaadin.ui.VerticalLayout;

public class PageSubject extends VerticalLayout implements Page {


	public PageSubject(BusEvenement busEvenement, Integer subjectId, com.vaadin.server.Page parentPage) {
		PresenterSubject presenter = new PresenterSubject(new ModelSubject(), new ViewCompetencyTable(busEvenement), new ViewModifySubject(busEvenement), busEvenement);
		presenter.setSubject(subjectId, parentPage);
		addComponent(presenter.getComponent());
	}

	private static final long serialVersionUID = 5894665749599379405L;

	
}
