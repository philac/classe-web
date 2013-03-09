package ca.classe.classe_web;


import ca.classe.classe_service.commun.BusEvenement;
import ca.classe.classe_service.commun.ContexteApplicationUtils;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("reindeer")
public class MyVaadinUI extends UI
{

	public static final String MAINVIEW = "";
	
	private Navigator navigator;
	private BusEvenement busEvenement = ContexteApplicationUtils.getInstance().getBusEvenement();
	
    @Override
    protected void init(VaadinRequest request) {
    	navigator = new Navigator(this, this);
    	
    	navigator.addView(MAINVIEW, new MainView(navigator));
    }
    
    public BusEvenement getBusEvenement() {
    	return busEvenement;
    }

}
