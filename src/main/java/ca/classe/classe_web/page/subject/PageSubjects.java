package ca.classe.classe_web.page.subject;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_web.page.Page;

import com.vaadin.ui.VerticalLayout;

public class PageSubjects extends VerticalLayout implements Page {

	private static final long serialVersionUID = -6557873439700914937L;

	public PageSubjects(BusEvenement busEvenement) {
		addComponent(new PresenterSubjects(new ModelSubject(), new ViewSubjectTable(busEvenement), busEvenement).getComponent());
	}	
}
