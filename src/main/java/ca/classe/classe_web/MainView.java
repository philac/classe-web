package ca.classe.classe_web;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.ContexteApplicationUtils;
import ca.classe.classe_web.menu.MainMenu;
import ca.classe.classe_web.page.enums.PageNames;
import ca.classe.classe_web.page.subject.PageSubject;
import ca.classe.classe_web.page.subject.PageSubjects;
import ca.classe.classe_web.page.subject.evenement.EvenementNavigateModifySubject;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View, EvenementNavigateModifySubject.Observer {

	private static final long serialVersionUID = -975397746367508677L;
	
	private Navigator navigator;
	private Panel content;

	private BusEvenement busEvenement = ContexteApplicationUtils.getInstance().getBusEvenement();

	public MainView(Navigator navigator) {
		Validate.notNull(navigator);
		
		this.navigator = navigator;
		
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
		
		Panel menu = new Panel();
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(new MainMenu(navigator));
		menu.setContent(menuContent);
		menu.setWidth(null);
		content = new Panel();
		content.setSizeFull();
		
		mainLayout.addComponent(menu);
		mainLayout.addComponent(content);
		mainLayout.setExpandRatio(content, 1.0F);
		addComponent(mainLayout);
		setExpandRatio(mainLayout, 1.0F);
		
		observe();
	}
	
	private void observe() {
		busEvenement.observer(EvenementNavigateModifySubject.TYPE, this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String params = event.getParameters();
		PageNames pageName = PageNames.getByUri(params);
		if (pageName != null) {
			switch (pageName) {
			case SUBJECT:
				event.getNavigator().getUI().getPage().setTitle(pageName.getTitle());
				content.setContent(new PageSubjects(busEvenement));
				break;
			default:
				break;
				
			}
		} else {
			if (params.contains(PageNames.SUBJECT.getUri()) && params.contains(PageNames.MODIFY.getUri())) {
				Integer subjectId = Integer.parseInt(StringUtils.substringBetween(params, PageNames.SUBJECT.getUri() + "/", "/" + PageNames.MODIFY.getUri()));
				content.setContent(new PageSubject(busEvenement, subjectId, event.getNavigator().getUI().getPage()));
			}
		}
	}

	@Override
	public void onNavigateToModify(Integer subjectId) {
		navigator.navigateTo(MyVaadinUI.MAINVIEW + "/" + PageNames.SUBJECT.getUri() + "/" + subjectId + "/" + PageNames.MODIFY.getUri());
	}

}
