package ca.classe.classe_web.mvp;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.ContexteApplicationUtils;
import ca.classe.classe_web.page.Page;

import com.vaadin.ui.VerticalLayout;

public class PageWithPresenter extends VerticalLayout implements Page {
	
	protected static PresenterFactory presenterFactory = new PresenterFactory(ContexteApplicationUtils.getService(BusEvenement.class));

	private static final long serialVersionUID = -2179417745159010042L;

}
