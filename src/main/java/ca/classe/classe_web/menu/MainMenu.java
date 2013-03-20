package ca.classe.classe_web.menu;

import ca.classe.classe_web.MyVaadinUI;
import ca.classe.classe_web.page.enums.PageNames;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class MainMenu extends VerticalLayout {

	private static final long serialVersionUID = -2656185174559504390L;
	
	private Navigator navigator;

	public MainMenu(Navigator navigator) {
		this.navigator = navigator;
		
		addComponent(new Button(PageNames.SUBJECT.getTitle(), new ButtonListener(PageNames.SUBJECT.getUri())));
		addComponent(new Button(PageNames.CLASSE.getTitle(), new ButtonListener(PageNames.CLASSE.getUri())));
	}
	
	private class ButtonListener implements Button.ClickListener {

		private static final long serialVersionUID = 8523330979367855676L;
		private String uri;
		
		public ButtonListener(String uri) {
			this.uri = uri;
		}
		
		@Override
		public void buttonClick(ClickEvent event) {
			navigator.navigateTo(MyVaadinUI.MAINVIEW + "/" + uri);
		}
		
	}
}
